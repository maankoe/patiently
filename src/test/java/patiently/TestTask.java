package patiently;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

public class TestTask {
    @Test
    public void testTask() {
        int threadId = 1;
        Task task = new Task(threadId, 0);
        assertThat(task.result()).isNotEqualTo(threadId);
        task.run();
        assertThat(task.result()).isEqualTo(threadId);
    }

    @Test
    public void testTaskOnThread() throws InterruptedException {
        int threadId = 1;
        long taskLengthMs = 250;
        Task task = new Task(threadId, taskLengthMs);
        assertThat(task.result()).isNotEqualTo(threadId);
        Executors.newSingleThreadExecutor().execute(task);
        assertThat(task.result()).isNotEqualTo(threadId);
        Thread.sleep(taskLengthMs*2);
        assertThat(task.result()).isEqualTo(threadId);
    }
}
