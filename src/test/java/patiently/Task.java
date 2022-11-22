package patiently;

public class Task implements Runnable {
    static class ResultBox {
        private boolean finished = false;
        public void setFinished() {
            this.finished = true;
        }
        public boolean getFinished() {
            return this.finished;
        }
    }

    private final long taskLengthMs;
    private ResultBox resultBox;

    public Task(long taskLengthMs) {
        this(taskLengthMs, new ResultBox());
    }

    public Task(long taskLengthMs, ResultBox resultBox) {
        this.taskLengthMs = taskLengthMs;
        this.resultBox = resultBox;
    }

    public boolean finished() {
        return this.resultBox.getFinished();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(this.taskLengthMs);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.resultBox.setFinished();
    }
}
