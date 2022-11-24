package patiently.builder;

import patiently.Patience;

import java.util.concurrent.Callable;

public class PatientBuilder extends BasePatientBuilder<PatientBuilder> {
    public <T> PatientAssertBuilder<T> waitFor(final Callable<T> assertion) {
        return new PatientAssertBuilder<>(this.retryScheduleBuilder, assertion);
    }

    public PatientVerifyBuilder waitFor(final Runnable assertion) {
        return new PatientVerifyBuilder(this.retryScheduleBuilder, assertion);
    }
}
