package patiently;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class PatientPredicate extends Patience<Void> {

    private final Supplier<Boolean> test;

    public PatientPredicate(Supplier<Boolean> test, RetrySchedule retries) {
        super(retries);
        this.test = test;
    }

    @Override
    protected Optional<Void> _test() {
        if (!this.test.get()) {
            throw new IllegalStateException("Predicate was false");
        }
        return Optional.empty();
    }
}
