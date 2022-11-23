package patiently;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

public class TestPatientAssert {
    @Test
    public void testTask() {
        Task task = new Task(0);
        task.run();
        new PatientAssert<>(() -> assertThat(task.finished()).isTrue()).test();
    }

    @Test
    public void testThreadedTask() {
        long taskLengthMs = 250;
        Task task = new Task(taskLengthMs);
        Executors.newSingleThreadExecutor().execute(task);
        new PatientAssert<>(() -> assertThat(task.finished()).isTrue()).test();
    }

    @Test
    public void testThreadedTaskTooEarly() {
        long taskLengthMs = 250;
        Task task = new Task(taskLengthMs);
        Executors.newSingleThreadExecutor().execute(task);
        new PatientAssert<>(() -> assertThat(task.finished())).test().get().isFalse();
    }
}
