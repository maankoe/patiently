package patiently.builder;

import patiently.PatientPredicate;

import java.util.function.Supplier;

public class PatientPredicateBuilder
        implements Builder<PatientPredicate> {

    private final RetryScheduleBuilder retryScheduleBuilder;
    private final Supplier<Boolean> assertion;

    public PatientPredicateBuilder(Supplier<Boolean> assertion) {
        super();
        this.assertion = assertion;
        this.retryScheduleBuilder = new RetryScheduleBuilder();
    }

    public PatientPredicateBuilder everyMs(final long pauseMs) {
        this.retryScheduleBuilder.setPauseMs(pauseMs);
        return this;
    }

    public void untilMs(long maxTimeMs) {
        this.retryScheduleBuilder.setMaxTimeMs(maxTimeMs);
        this.test();
    }

    public PatientPredicate build() {
        return new PatientPredicate(
                this.assertion,
                this.retryScheduleBuilder.build()
        );
    }

    protected void test() {
        this.build().test();
    }
}
