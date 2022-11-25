package patiently.builder;

import patiently.PatientCallable;

import java.util.concurrent.Callable;

public class PatientCallableBuilder<T>
        extends BasePatientBuilder<PatientCallableBuilder<T>>
        implements Builder<PatientCallable<T>> {
    private final RetryScheduleBuilder retryScheduleBuilder;
    private final Callable<T> assertion;

    public PatientCallableBuilder(
            RetryScheduleBuilder retryScheduleBuilder,
            Callable<T> assertion
    ) {
        this.retryScheduleBuilder = retryScheduleBuilder;
        this.assertion = assertion;
    }

    public PatientCallable<T> build() {
        return new PatientCallable<>(
                this.assertion,
                this.retryScheduleBuilder.build()
        );
    }

    public T test() {
        return this.build().test().orElse(null);
    }
}
