package patiently;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestPatientVerify {
    @Test
    public void testTask() {
        Task.ResultBox resultBox = mock(Task.ResultBox.class);
        Task task = new Task(0, resultBox);
        task.run();
        new PatientVerify(() -> verify(resultBox).setFinished()).test();
    }

    @Test
    public void testThreadedTask() {
        Task.ResultBox resultBox = mock(Task.ResultBox.class);
        long taskLengthMs = 250;
        Task task = new Task(taskLengthMs, resultBox);
        Executors.newSingleThreadExecutor().execute(task);
        new PatientVerify(() -> verify(resultBox).setFinished()).test();
    }

    @Test
    public void testThreadedTaskTooEarly() {
        Task.ResultBox resultBox = mock(Task.ResultBox.class);
        long taskLengthMs = 250;
        Task task = new Task(taskLengthMs, resultBox);
        Executors.newSingleThreadExecutor().execute(task);
        new PatientVerify(() -> verify(resultBox).setFinished()).test();
    }
}
