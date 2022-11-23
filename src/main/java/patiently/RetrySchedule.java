package patiently;

import java.util.Iterator;

public class RetrySchedule implements Iterable<Retry> {
    private final int maxRetries;
    private final Retry retry;

    public RetrySchedule(int maxRetries, Retry retry) {
        if (maxRetries <= 0) {
            throw new IllegalArgumentException("Argument maxRetries must be positive, provided: "+maxRetries);
        }
        this.maxRetries = maxRetries;
        this.retry = retry;
    }

    public Iterator<Retry> iterator() {
        return new Iterator<>() {
            private int numRetries;

            @Override
            public boolean hasNext() {
                return (numRetries++ < maxRetries);
            }

            @Override
            public Retry next() {
                return retry;
            }
        };
    }
}
