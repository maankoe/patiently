package patiently;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class TestPatientBoolean {
    private final RetrySchedule retries = new RetrySchedule(1000, new Retry(100));
    private final int taskLengthMs = 250;

    @Test
    public void testTask() {
        Task task = new Task(0);
        task.run();
        new PatientBoolean(task::finished, retries).execute();
    }

    @Test
    public void testThreadedTask() {
        Task task = new Task(taskLengthMs);
        Executors.newSingleThreadExecutor().execute(task);
        new PatientBoolean(task::finished, retries).execute();
    }

    @Test
    public void testThreadedTaskTestFails() {
        Task task = new Task(taskLengthMs);
        Executors.newSingleThreadExecutor().execute(task);
        RetrySchedule retries = new RetrySchedule(1, new Retry(1));
        Throwable error = catchThrowable(() ->
                new PatientBoolean(task::finished, retries).execute()
        );
        assertThat(error).isInstanceOf(IllegalStateException.class);
    }
}
