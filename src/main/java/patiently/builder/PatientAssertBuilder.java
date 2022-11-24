package patiently.builder;

import patiently.PatientAssert;

import java.util.concurrent.Callable;

public class PatientAssertBuilder<T>
        extends BasePatientBuilder<PatientAssertBuilder<T>>
        implements Builder<PatientAssert<T>> {
    private final RetryScheduleBuilder retryScheduleBuilder;
    private final Callable<T> assertion;

    public PatientAssertBuilder(
            RetryScheduleBuilder retryScheduleBuilder,
            Callable<T> assertion
    ) {
        this.retryScheduleBuilder = retryScheduleBuilder;
        this.assertion = assertion;
    }

    public PatientAssert<T> build() {
        return new PatientAssert<>(
                this.assertion,
                this.retryScheduleBuilder.build()
        );
    }

    public T test() {
        return this.build().test().orElse(null);
    }
}
