package patiently;


import java.util.function.Supplier;

public class PatientAssert<T> extends Patience<T> {
    private final Supplier<T> test;

    public PatientAssert(Supplier<T> test) {
        this.test = test;
    }

    @Override
    protected T _test() {
        return test.get();
    }
}
