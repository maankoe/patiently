package patiently;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

import static org.mockito.Mockito.*;

public class TestTaskVerify {
    @Test
    public void testTaskVerify() {
        Task.ResultBox resultBox = mock(Task.ResultBox.class);
        Task task = new Task(0, resultBox);
        verifyNoInteractions(resultBox);
        task.run();
        verify(resultBox).setFinished();
    }

    @Test
    public void testTaskVerifyOnThread() throws InterruptedException {
        Task.ResultBox resultBox = mock(Task.ResultBox.class);
        long taskLengthMs = 250;
        Task task = new Task(taskLengthMs, resultBox);
        verifyNoInteractions(resultBox);
        Executors.newSingleThreadExecutor().execute(task);
        verifyNoInteractions(resultBox);
        Thread.sleep(taskLengthMs*2);
        verify(resultBox).setFinished();
    }
}
