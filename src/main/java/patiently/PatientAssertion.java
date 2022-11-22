package patiently;


import java.util.function.Supplier;

public class PatientAssertion<T> {
    private final Supplier<T> test;
    private final int retries = 10;

    public PatientAssertion(Supplier<T> test) {
        this.test = test;
    }

    public T test() {
        for (int i=0;i<this.retries;i++) {
            try {
                return test.get();
            } catch (Throwable ignored) {}
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return test.get();
    }
}
