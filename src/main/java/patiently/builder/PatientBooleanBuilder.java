package patiently.builder;

import patiently.PatientBoolean;

import java.util.function.Supplier;

public class PatientBooleanBuilder extends VoidPatientBuilder<PatientBoolean> {

    private final Supplier<Boolean> task;

    public PatientBooleanBuilder(Supplier<Boolean> task) {
        super();
        this.task = task;
    }

    public PatientBoolean build() {
        return new PatientBoolean(
                this.task,
                this.retryScheduleBuilder.build()
        );
    }
}
