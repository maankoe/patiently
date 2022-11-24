package patiently.builder;

import patiently.Retry;
import patiently.RetrySchedule;

public class RetryScheduleBuilder implements Builder<RetrySchedule> {
    private long time;
    private int maxRetries;

    public RetryScheduleBuilder withTime(final long time) {
        this.time = time;
        return this;
    }

    public RetryScheduleBuilder withRetries(final int maxRetries) {
        this.maxRetries = maxRetries;
        return this;
    }

    public RetrySchedule build() {
        return new RetrySchedule(
                this.maxRetries,
                new Retry(this.time)
        );
    }
}
