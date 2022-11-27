package patiently;

import java.util.Optional;

public abstract class PatientBase<T> {
    private final RetrySchedule retries;

    public PatientBase(RetrySchedule retries) {
        this.retries = retries;
    }

    protected abstract Optional<T> _execute();

    public Optional<T> execute() {
        for (Retry retry : retries) {
            try {
                return this._execute();
            } catch (Throwable ignored) {}
            try {
                retry.pause();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return this._execute();
    }
}
