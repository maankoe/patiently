package patiently;

import java.util.Optional;
import java.util.concurrent.Callable;

public class PatientCallable<T> extends PatientBase<T> {
    private final Callable<T> callable;

    public PatientCallable(Callable<T> callable, RetrySchedule retries) {
        super(retries);
        this.callable = callable;
    }

    @Override
    protected Optional<T> _execute() {
        try {
            return Optional.of(callable.call());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
