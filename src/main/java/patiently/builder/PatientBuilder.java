package patiently.builder;

import patiently.PatientBase;

public abstract class PatientBuilder<P extends PatientBase<?>, B extends PatientBuilder<P, B>>
        implements Builder<P> {

    protected final RetryScheduleBuilder retryScheduleBuilder;

    public PatientBuilder() {
        this.retryScheduleBuilder = new RetryScheduleBuilder();
    }

    public B every(final long pauseMs) {
        this.retryScheduleBuilder.setPauseMs(pauseMs);
        return (B) this;
    }
}
