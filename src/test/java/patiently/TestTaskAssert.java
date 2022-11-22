package patiently;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

public class TestTaskAssert {
    @Test
    public void testTask() {
        Task task = new Task(0);
        assertThat(task.finished()).isFalse();
        task.run();
        assertThat(task.finished()).isTrue();
    }

    @Test
    public void testTaskOnThread() throws InterruptedException {
        long taskLengthMs = 250;
        Task task = new Task(taskLengthMs);
        assertThat(task.finished()).isFalse();
        Executors.newSingleThreadExecutor().execute(task);
        assertThat(task.finished()).isFalse();
        Thread.sleep(taskLengthMs*2);
        assertThat(task.finished()).isTrue();
    }
}
