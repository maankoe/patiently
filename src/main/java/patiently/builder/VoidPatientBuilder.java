package patiently.builder;

import patiently.PatientBase;

public abstract class VoidPatientBuilder<P extends PatientBase<Void>>
        extends PatientBuilder<P, VoidPatientBuilder<P>> {

    /**
     * Patiently retries execution (as specified in the builder) until success (no Exception/Throw) or
     * until specified time have passed.
     * @param maxTimeMs   Maximum time (in milliseconds) after which it stops retrying.
     */
    public void until(long maxTimeMs) {
        this.retryScheduleBuilder.setMaxTimeMs(maxTimeMs);
        this.test();
    }

    private void test() {
        this.build().execute();
    }
}
