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
    public void testRetriesIterable() {
        int maxRetries = 10;
        RetrySchedule retries = new RetrySchedule(maxRetries, mock(Retry.class));
        assertThat(Lists.newArrayList(retries)).hasSize(maxRetries);
    }

    @Test
    public void testRetriesPositivePause() throws InterruptedException {
        Exception e = catchException(() -> new Retry(0));
        assertThat(e)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("pauseMs must be positive");
    }

    @Test
    public void testRetriesPositiveMaxRetries() throws InterruptedException {
        Exception e = catchException(() -> new RetrySchedule(0, mock(Retry.class)));
        assertThat(e)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("maxRetries must be positive");
    }
}
