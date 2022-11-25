package patiently;

import java.util.Optional;

public class PatientRunnable extends Patience<Void> {
    private final Runnable test;

    public PatientRunnable(Runnable test, RetrySchedule retries) {
        super(retries);
        this.test = test;
    }

    public Optional<Void> _test() {
        test.run();
        return Optional.empty();
    }
}
