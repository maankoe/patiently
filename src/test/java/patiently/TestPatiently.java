package patiently;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

public class TestPatiently {
    @Test
    public void testTask() {
        int threadId = 1;
        Task task = new Task(threadId, 0);
        task.run();
        new PatientAssertion<>(() -> assertThat(task.result()).isEqualTo(threadId)).test();
    }

    @Test
    public void testThreadedTask() {
        int threadId = 1;
        long taskLengthMs = 250;
        Task task = new Task(threadId, taskLengthMs);
        Executors.newSingleThreadExecutor().execute(task);
        new PatientAssertion<>(() -> assertThat(task.result()).isEqualTo(threadId)).test();
    }

    @Test
    public void testThreadedTaskTooEarly() {
        int threadId = 1;
        long taskLengthMs = 250;
        Task task = new Task(threadId, taskLengthMs);
        Executors.newSingleThreadExecutor().execute(task);
        new PatientAssertion<>(() -> assertThat(task.result())).test().isNotEqualTo(threadId);
    }
}
