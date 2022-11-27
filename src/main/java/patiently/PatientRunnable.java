package patiently;

import java.util.Optional;

public class PatientRunnable extends PatientBase<Void> {
    private final Runnable runnable;

    public PatientRunnable(Runnable runnable, RetrySchedule retries) {
        super(retries);
        this.runnable = runnable;
    }

    public Optional<Void> _execute() {
        runnable.run();
        return Optional.empty();
    }
}
