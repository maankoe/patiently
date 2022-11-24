package patiently.builder;

import java.util.concurrent.Callable;

abstract class BasePatientBuilder<S extends BasePatientBuilder<S>> {
    protected final RetryScheduleBuilder retryScheduleBuilder;

    public BasePatientBuilder() {
        this.retryScheduleBuilder = new RetryScheduleBuilder();
    }

    public <T> PatientAssertBuilder<T> waitFor(final Callable<T> assertion) {
        return new PatientAssertBuilder<>(this.retryScheduleBuilder, assertion);
    }

    public PatientVerifyBuilder waitFor(final Runnable assertion) {
        return new PatientVerifyBuilder(this.retryScheduleBuilder, assertion);
    }

    public S retrying(final int maxRetries) {
        this.retryScheduleBuilder.withRetries(maxRetries);
        return (S) this;
    }

    public S every(final long time) {
        this.retryScheduleBuilder.withTime(time);
        return (S) this;
    }
}
