package patiently.builder;

import patiently.PatientRunnable;

public class PatientRunnableBuilder extends VoidPatientBuilder<PatientRunnable> {

    private final Runnable task;

    public PatientRunnableBuilder(Runnable task) {
        super();
        this.task = task;
    }

    public PatientRunnable build() {
        return new PatientRunnable(
                this.task,
                this.retryScheduleBuilder.build()
        );
    }
}
