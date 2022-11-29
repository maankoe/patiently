package patiently;

import java.util.Optional;

public abstract class PatientBase<T> {
    private final RetrySchedule retries;

    public PatientBase(RetrySchedule retries) {
        this.retries = retries;
    }

    /**
     * Single execution of the underlying task.
     * @return If execution is successful (no Exception/Throws), Optional of the
     * underlying task is returned. In cases where this is void (e.g., Runnable),
     * Optional.empty() is provided). If execution is unsuccessful, Exception
     * (as defined by the underlying task) is thrown.
     */
    protected abstract Optional<T> _execute();

    /**
     * Executes (as specified in extending class) for the given RetrySchedule.
     * @return If execution is successful (no Exception/Throws), Optional of the
     * underlying task is returned. In cases where this is void (e.g., Runnable),
     * Optional.empty() is provided). If execution is unsuccessful, Exception
     * (as defined by the underlying task) is thrown.
     */
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
