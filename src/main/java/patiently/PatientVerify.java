package patiently;

import java.util.Optional;

public class PatientVerify extends Patience<Void> {
    private final Runnable test;

    public PatientVerify(Runnable test, RetrySchedule retries) {
        super(retries);
        this.test = test;
    }

    public Optional<Void> _test() {
        test.run();
        return Optional.empty();
    }
}
