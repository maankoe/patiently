package patiently;

import java.util.Optional;
import java.util.function.Supplier;

public class PatientBoolean extends PatientBase<Void> {

    private final Supplier<Boolean> task;

    public PatientBoolean(Supplier<Boolean> task, RetrySchedule retries) {
        super(retries);
        this.task = task;
    }

    @Override
    protected Optional<Void> _execute() {
        if (!this.task.get()) {
            throw new IllegalStateException("Result was false");
        }
        return Optional.empty();
    }
}
