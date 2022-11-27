package patiently.builder;

import patiently.PatientBase;

public abstract class TPatientBuilder<T, P extends PatientBase<T>>
        extends PatientBuilder<P, TPatientBuilder<T, P>> {

    public T until(long maxTimeMs) {
        this.retryScheduleBuilder.setMaxTimeMs(maxTimeMs);
        return this.test();
    }

    private T test() {
        return this.build().execute().orElse(null);
    }
}
