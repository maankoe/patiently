package patiently;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestBuilder {

    private final int taskLengthMs = 250;
    private final long everyMs = 100;
    private final long untilMs = 500;

    @Test
    public void testPatientCallable() {
        Task task = new Task(taskLengthMs);
        Executors.newSingleThreadExecutor().execute(task);
        Patiently.retry(() -> assertThat(task.finished()).isTrue())
                .every(everyMs)
                .until(untilMs);
    }

    @Test
    public void testPatientRunnable() {
        Task.ResultBox resultBox = mock(Task.ResultBox.class);
        Task task = new Task(taskLengthMs, resultBox);
        Executors.newSingleThreadExecutor().execute(task);
        Patiently.retry(() -> verify(resultBox).setFinished())
                .every(everyMs)
                .until(untilMs);
    }

    @Test
    public void testPatientBoolean() {
        Task task = new Task(taskLengthMs);
        Executors.newSingleThreadExecutor().execute(task);
        Patiently.retry(task::finished)
                .every(everyMs)
                .until(untilMs);
    }
}
