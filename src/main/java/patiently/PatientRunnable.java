package patiently;

import java.util.Optional;

public class PatientRunnable extends PatientBase<Void> {
    private final Runnable task;

    public PatientRunnable(Runnable task, RetrySchedule retries) {
        super(retries);
        this.task = task;
    }

    public Optional<Void> _execute() {
        task.run();
        return Optional.empty();
    }
}
