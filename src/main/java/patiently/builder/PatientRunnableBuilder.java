package patiently.builder;

import patiently.PatientRunnable;

public class PatientRunnableBuilder
        implements Builder<PatientRunnable> {

    private final Runnable assertion;
    private final RetryScheduleBuilder retryScheduleBuilder;

    public PatientRunnableBuilder(Runnable assertion) {
        super();
        this.assertion = assertion;
        this.retryScheduleBuilder = new RetryScheduleBuilder();
    }

    public PatientRunnableBuilder everyMs(final long pauseMs) {
        this.retryScheduleBuilder.setPauseMs(pauseMs);
        return this;
    }

    public void untilMs(long maxTimeMs) {
        this.retryScheduleBuilder.setMaxTimeMs(maxTimeMs);
        this.test();
    }

    public PatientRunnable build() {
        return new PatientRunnable(
                this.assertion,
                this.retryScheduleBuilder.build()
        );
    }

    private void test() {
        this.build().execute();
    }
}
