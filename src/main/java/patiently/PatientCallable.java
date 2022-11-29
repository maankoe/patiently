package patiently;

import java.util.Optional;
import java.util.concurrent.Callable;

public class PatientCallable<T> extends PatientBase<T> {
    private final Callable<T> task;

    public PatientCallable(Callable<T> task, RetrySchedule retries) {
        super(retries);
        this.task = task;
    }

    @Override
    protected Optional<T> _execute() {
        try {
            return Optional.of(task.call());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
