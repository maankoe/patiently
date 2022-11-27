package patiently.builder;

import patiently.PatientCallable;

import java.util.concurrent.Callable;

public class PatientCallableBuilder<T> extends TPatientBuilder<T, PatientCallable<T>> {

    private final Callable<T> assertion;

    public PatientCallableBuilder(Callable<T> assertion) {
        super();
        this.assertion = assertion;
    }

    public PatientCallable<T> build() {
        return new PatientCallable<>(
                this.assertion,
                this.retryScheduleBuilder.build()
        );
    }
}
