package patiently;

public class Task implements Runnable {
    private final long taskLengthMs;
    private final int threadId;
    private int result = -1;
    public Task(int threadId, long taskLengthMs) {
        this.threadId = threadId;
        this.taskLengthMs = taskLengthMs;
    }

    public int result() {
        return this.result;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(this.taskLengthMs);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.result = this.threadId;
    }
}
