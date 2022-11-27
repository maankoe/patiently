package patiently.builder;

import patiently.PatientRunnable;

public class PatientRunnableBuilder extends VoidPatientBuilder<PatientRunnable> {

    private final Runnable assertion;

    public PatientRunnableBuilder(Runnable assertion) {
        super();
        this.assertion = assertion;
    }

    public PatientRunnable build() {
        return new PatientRunnable(
                this.assertion,
                this.retryScheduleBuilder.build()
        );
    }
}
