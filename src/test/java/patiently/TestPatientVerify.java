package patiently;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestPatientVerify {

    private final RetrySchedule retries = new RetrySchedule(10, new Retry(100));
    private final int taskLengthMs = 250;

    @Test
    public void testTask() {
        Task.ResultBox resultBox = mock(Task.ResultBox.class);
        Task task = new Task(0, resultBox);
        task.run();
        new PatientVerify(() -> verify(resultBox).setFinished(), retries).test();
    }

    @Test
    public void testThreadedTask() {
        Task.ResultBox resultBox = mock(Task.ResultBox.class);
        Task task = new Task(taskLengthMs, resultBox);
        Executors.newSingleThreadExecutor().execute(task);
        new PatientVerify(() -> verify(resultBox).setFinished(), retries).test();
    }

    @Test
    public void testThreadedTaskTooEarly() {
        Task.ResultBox resultBox = mock(Task.ResultBox.class);
        Task task = new Task(taskLengthMs, resultBox);
        Executors.newSingleThreadExecutor().execute(task);
        new PatientVerify(() -> verify(resultBox).setFinished(), retries).test();
    }
}
