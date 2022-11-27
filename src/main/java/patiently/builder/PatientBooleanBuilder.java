package patiently.builder;

import patiently.PatientBoolean;

import java.util.function.Supplier;

public class PatientBooleanBuilder extends VoidPatientBuilder<PatientBoolean> {

    private final Supplier<Boolean> assertion;

    public PatientBooleanBuilder(Supplier<Boolean> assertion) {
        super();
        this.assertion = assertion;
    }

    public PatientBoolean build() {
        return new PatientBoolean(
                this.assertion,
                this.retryScheduleBuilder.build()
        );
    }
}
