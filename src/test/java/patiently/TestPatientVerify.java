package patiently;

import org.junit.jupiter.api.Test;
import org.mockito.exceptions.verification.WantedButNotInvoked;

import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.*;
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
    public void testThreadedTaskTestFails() {
        Task.ResultBox resultBox = mock(Task.ResultBox.class);
        Task task = new Task(taskLengthMs, resultBox);
        Executors.newSingleThreadExecutor().execute(task);
        RetrySchedule retries = new RetrySchedule(1, new Retry(1));
        Throwable error = catchThrowable(() ->
                new PatientVerify(() -> verify(resultBox).setFinished(), retries).test()
        );
        assertThat(error).isInstanceOf(WantedButNotInvoked.class);
    }
}
