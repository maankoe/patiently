package patiently.builder;

import patiently.PatientCallable;

import java.util.concurrent.Callable;

public class PatientCallableBuilder<T>
        implements Builder<PatientCallable<T>> {

    private final RetryScheduleBuilder retryScheduleBuilder;
    private final Callable<T> assertion;

    public PatientCallableBuilder(Callable<T> assertion) {
        super();
        this.assertion = assertion;
        this.retryScheduleBuilder = new RetryScheduleBuilder();
    }

    public PatientCallableBuilder<T> everyMs(final long pauseMs) {
        this.retryScheduleBuilder.setPauseMs(pauseMs);
        return this;
    }

    public T untilMs(long maxTimeMs) {
        this.retryScheduleBuilder.setMaxTimeMs(maxTimeMs);
        return this.test();
    }

    public PatientCallable<T> build() {
        return new PatientCallable<>(
                this.assertion,
                this.retryScheduleBuilder.build()
        );
    }

    protected T test() {
        return this.build().execute().orElse(null);
    }
}
