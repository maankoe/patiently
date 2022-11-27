package patiently;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;
import static org.mockito.Mockito.*;

public class TestRetrySchedule {
    @Test
    public void testRetry() throws InterruptedException {
        int pauseMs = 100;
        Retry retry = new Retry(pauseMs);
        long start = System.currentTimeMillis();
        retry.pause();
        assertThat(System.currentTimeMillis() - start).isGreaterThanOrEqualTo(pauseMs);
    }

    @Test
    public void testNumberOfRetries() throws InterruptedException {
        int maxTimeMs = 100;
        int pauseMs = 10;
        RetrySchedule retries = new RetrySchedule(maxTimeMs, new Retry(pauseMs));
        int numRetries = 0;
        for (Retry retry : retries) {
            numRetries++;
            retry.pause();
        }
        assertThat(numRetries).isEqualTo(maxTimeMs / pauseMs);
    }

    @Test
    public void testRetriesPositivePause() {
        Exception e = catchException(() -> new Retry(0));
        assertThat(e)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("pauseMs must be positive");
    }

    @Test
    public void testRetriesPositiveMaxRetries() {
        Exception e = catchException(() -> new RetrySchedule(0, mock(Retry.class)));
        assertThat(e)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("maxTimeMs must be positive");
    }
}
