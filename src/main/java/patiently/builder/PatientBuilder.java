package patiently.builder;

import java.util.concurrent.Callable;

public class PatientBuilder extends BasePatientBuilder<PatientBuilder> {
    public <T> PatientAssertBuilder<T> recheck(final Callable<T> assertion) {
        return new PatientAssertBuilder<>(this.retryScheduleBuilder, assertion);
    }

    public PatientVerifyBuilder recheck(final Runnable assertion) {
        return new PatientVerifyBuilder(this.retryScheduleBuilder, assertion);
    }
}
