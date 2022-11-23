package patiently;

public class Retry {
    private final long pauseMs;

    public Retry(long pauseMs) {
        if (pauseMs <= 0) {
            throw new IllegalArgumentException("Argument pauseMs must be positive, provided: "+pauseMs);
        }
        this.pauseMs = pauseMs;
    }

    public void pause() throws InterruptedException {
        Thread.sleep(this.pauseMs);
    }
}
