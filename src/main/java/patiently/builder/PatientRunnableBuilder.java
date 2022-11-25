package patiently.builder;


import patiently.PatientRunnable;

public class PatientRunnableBuilder
        extends BasePatientBuilder<PatientRunnableBuilder>
        implements Builder<PatientRunnable> {
    private final RetryScheduleBuilder retryScheduleBuilder;
    private final Runnable assertion;

    public PatientRunnableBuilder(
            RetryScheduleBuilder retryScheduleBuilder,
            Runnable assertion
    ) {
        this.retryScheduleBuilder = retryScheduleBuilder;
        this.assertion = assertion;
    }

    public PatientRunnable build() {
        return new PatientRunnable(
                this.assertion,
                this.retryScheduleBuilder.build()
        );
    }

    public void test() {
        this.build().test();
    }
}
