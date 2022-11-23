package patiently;


import java.util.Optional;
import java.util.function.Supplier;

public class PatientAssert<T> extends Patience<T> {
    private final Supplier<T> test;

    public PatientAssert(Supplier<T> test) {
        this.test = test;
    }

    @Override
    protected Optional<T> _test() {
        return Optional.of(test.get());
    }
}
