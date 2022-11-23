package patiently;

import java.util.Optional;

public abstract class Patience<T> {
    private final RetrySchedule retries;

    public Patience(RetrySchedule retries) {
        this.retries = retries;
    }

    protected abstract Optional<T> _test();

    public Optional<T> test() {
        for (Retry retry : retries) {
            try {
                return this._test();
            } catch (Throwable ignored) {}
            try {
                retry.pause();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return this._test();
    }
}
