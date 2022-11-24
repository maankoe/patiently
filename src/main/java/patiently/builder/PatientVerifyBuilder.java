package patiently.builder;


import patiently.PatientVerify;

public class PatientVerifyBuilder
        extends BasePatientBuilder<PatientVerifyBuilder>
        implements Builder<PatientVerify> {
    private final RetryScheduleBuilder retryScheduleBuilder;
    private final Runnable assertion;

    public PatientVerifyBuilder(
            RetryScheduleBuilder retryScheduleBuilder,
            Runnable assertion
    ) {
        this.retryScheduleBuilder = retryScheduleBuilder;
        this.assertion = assertion;
    }

    public PatientVerify build() {
        return new PatientVerify(
                this.assertion,
                this.retryScheduleBuilder.build()
        );
    }

    public void test() {
        this.build().test();
    }
}
