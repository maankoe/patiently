package patiently.builder;

import patiently.Retry;
import patiently.RetrySchedule;

public class RetryScheduleBuilder implements Builder<RetrySchedule> {
    private long pauseMs;
    private long maxTimeMs;

    public void setPauseMs(final long pauseMs) {
        this.pauseMs = pauseMs;
    }

    public void setMaxTimeMs(long maxTimeMs) {
        this.maxTimeMs = maxTimeMs;
    }

    public RetrySchedule build() {
        return new RetrySchedule(
                (int) (this.maxTimeMs / this.pauseMs),
                new Retry(this.pauseMs)
        );
    }


}
