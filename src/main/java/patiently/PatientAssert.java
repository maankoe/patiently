package patiently;


import java.util.Optional;
import java.util.concurrent.Callable;

public class PatientAssert<T> extends Patience<T> {
    private final Callable<T> test;

    public PatientAssert(Callable<T> test, RetrySchedule retries) {
        super(retries);
        this.test = test;
    }

    @Override
    protected Optional<T> _test() {
        try {
            return Optional.of(test.call());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
