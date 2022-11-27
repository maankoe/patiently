package patiently.builder;

import patiently.PatientBase;

public abstract class VoidPatientBuilder<P extends PatientBase<Void>>
        extends PatientBuilder<P, VoidPatientBuilder<P>> {

    public void until(long maxTimeMs) {
        this.retryScheduleBuilder.setMaxTimeMs(maxTimeMs);
        this.test();
    }

    private void test() {
        this.build().execute();
    }
}
