package patiently;

import java.util.Iterator;

public class RetrySchedule implements Iterable<Retry> {
    private final long maxTimeMs;
    private final Retry retry;

    public RetrySchedule(long maxTimeMs, Retry retry) {
        if (maxTimeMs <= 0) {
            throw new IllegalArgumentException("Argument maxTimeMs must be positive, provided: "+maxTimeMs);
        }
        this.maxTimeMs = maxTimeMs;
        this.retry = retry;
    }

    public Iterator<Retry> iterator() {
        return new Iterator<>() {
            private long startTime = 0;

            @Override
            public boolean hasNext() {
                return startTime == 0 ||  System.currentTimeMillis() - this.startTime < maxTimeMs;
            }

            @Override
            public Retry next() {
                if (startTime == 0) {
                    this.startTime = System.currentTimeMillis();
                }
                return retry;
            }
        };
    }
}
