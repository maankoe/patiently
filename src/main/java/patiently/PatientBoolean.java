package patiently;

import java.util.Optional;
import java.util.function.Supplier;

public class PatientBoolean extends PatientBase<Void> {

    private final Supplier<Boolean> supplier;

    public PatientBoolean(Supplier<Boolean> supplier, RetrySchedule retries) {
        super(retries);
        this.supplier = supplier;
    }

    @Override
    protected Optional<Void> _execute() {
        if (!this.supplier.get()) {
            throw new IllegalStateException("Result was false");
        }
        return Optional.empty();
    }
}
