package patiently.builder;

import patiently.PatientPredicate;

import java.util.function.Supplier;

public class PatientPredicateBuilder
        extends BasePatientBuilder<PatientPredicateBuilder>
        implements Builder<PatientPredicate> {
    private final RetryScheduleBuilder retryScheduleBuilder;
    private final Supplier<Boolean> assertion;

    public PatientPredicateBuilder(
            RetryScheduleBuilder retryScheduleBuilder,
            Supplier<Boolean> assertion
    ) {
        this.retryScheduleBuilder = retryScheduleBuilder;
        this.assertion = assertion;
    }

    public PatientPredicate build() {
        return new PatientPredicate(
                this.assertion,
                this.retryScheduleBuilder.build()
        );
    }

    public void test() {
        this.build().test();
    }
}
