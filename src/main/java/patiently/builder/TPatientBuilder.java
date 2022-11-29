package patiently.builder;

import patiently.PatientBase;

public abstract class TPatientBuilder<T, P extends PatientBase<T>>
        extends PatientBuilder<P, TPatientBuilder<T, P>> {
    /**
     * Patiently retries execution (as specified in the builder) until success (no Exception/Throw) or
     * until specified time have passed.
     * @param maxTimeMs   Maximum time (in milliseconds) after which it stops retrying.
     * @return            Return value of callable (if successful). An exception is thrown otherwise.
     */
    public T until(long maxTimeMs) {
        this.retryScheduleBuilder.setMaxTimeMs(maxTimeMs);
        return this.test();
    }

    private T test() {
        return this.build().execute().orElse(null);
    }
}
