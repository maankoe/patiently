package patiently;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.*;

public class TestPatientAssert {

    private final RetrySchedule retries = new RetrySchedule(10, new Retry(100));
    private final int taskLengthMs = 250;

    @Test
    public void testTask() {
        Task task = new Task(0);
        task.run();
        new PatientAssert<>(() -> assertThat(task.finished()).isTrue(), retries).test();
    }

    @Test
    public void testThreadedTask() {
        Task task = new Task(taskLengthMs);
        Executors.newSingleThreadExecutor().execute(task);
        new PatientAssert<>(() -> assertThat(task.finished()).isTrue(), retries).test();
    }

    @Test
    public void testThreadedTaskAssertFails() {
        Task task = new Task(taskLengthMs);
        Executors.newSingleThreadExecutor().execute(task);
        RetrySchedule retries = new RetrySchedule(1, new Retry(1));
        Throwable error = catchThrowable(() ->
                new PatientAssert<>(() -> assertThat(task.finished()).isTrue(), retries).test()
        );
        assertThat(error).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void testThreadedTaskGetResult() {
        Task task = new Task(taskLengthMs);
        Executors.newSingleThreadExecutor().execute(task);
        new PatientAssert<>(() -> assertThat(task.finished()), retries).test().get().isFalse();
    }
}
