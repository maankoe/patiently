package patiently;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class TestPatientPredicate {
    private final RetrySchedule retries = new RetrySchedule(10, new Retry(100));
    private final int taskLengthMs = 250;

    @Test
    public void testTask() {
        Task task = new Task(0);
        task.run();
        new PatientPredicate(task::finished, retries).test();
    }

    @Test
    public void testThreadedTask() {
        Task task = new Task(taskLengthMs);
        Executors.newSingleThreadExecutor().execute(task);
        new PatientPredicate(task::finished, retries).test();
    }

    @Test
    public void testThreadedTaskTestFails() {
        Task task = new Task(taskLengthMs);
        Executors.newSingleThreadExecutor().execute(task);
        RetrySchedule retries = new RetrySchedule(1, new Retry(1));
        Throwable error = catchThrowable(() ->
                new PatientPredicate(task::finished, retries).test()
        );
        assertThat(error).isInstanceOf(IllegalStateException.class);
    }
}
