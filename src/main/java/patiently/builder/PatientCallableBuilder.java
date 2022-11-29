package patiently.builder;

import patiently.PatientCallable;

import java.util.concurrent.Callable;

public class PatientCallableBuilder<T> extends TPatientBuilder<T, PatientCallable<T>> {

    private final Callable<T> task;

    public PatientCallableBuilder(Callable<T> task) {
        super();
        this.task = task;
    }

    public PatientCallable<T> build() {
        return new PatientCallable<>(
                this.task,
                this.retryScheduleBuilder.build()
        );
    }
}
