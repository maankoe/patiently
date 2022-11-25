package patiently.builder;

import java.util.concurrent.Callable;

abstract class BasePatientBuilder<S extends BasePatientBuilder<S>> {
    protected final RetryScheduleBuilder retryScheduleBuilder;

    public BasePatientBuilder() {
        this.retryScheduleBuilder = new RetryScheduleBuilder();
    }

    public <T> PatientCallableBuilder<T> recheck(final Callable<T> assertion) {
        return new PatientCallableBuilder<>(this.retryScheduleBuilder, assertion);
    }

    public PatientRunnableBuilder recheck(final Runnable assertion) {
        return new PatientRunnableBuilder(this.retryScheduleBuilder, assertion);
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
