package patiently;

import patiently.builder.PatientCallableBuilder;
import patiently.builder.PatientBooleanBuilder;
import patiently.builder.PatientRunnableBuilder;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class Patiently {
    /**
     * Creates a patient builder for a Callable (e.g., Assertions.assertThat in assertj)
     * @param callable    The Callable to be evaluated
     * @return            Builder for PatientCallable
     * @param <T>         Return type of the callable to be evaluated
     * @see PatientCallableBuilder
     * @see PatientCallable
     */
    public static <T> PatientCallableBuilder<T> retry(Callable<T> callable) {
        return new PatientCallableBuilder<>(callable);
    }

    /**
     * Creates a patient builder for a Runnable (e.g., Mockito.verify in mockito).
     * @param runnable    The Runnable to be evaluated.
     * @return            Builder for PatientRunnable.
     * @see PatientRunnableBuilder
     * @see PatientRunnable
     */
    public static <T> PatientRunnableBuilder retry(Runnable runnable) {
        return new PatientRunnableBuilder(runnable);
    }

    /**
     * Creates a patient builder for a boolean Supplier (e.g., a general boolean evaluation).
     * @param booleanSupplier    The boolean Supplier to be evaluated.
     * @return                   Builder for PatientBoolean.
     * @see PatientBooleanBuilder
     * @see PatientBoolean
     */
    public static <T> PatientBooleanBuilder retry(Supplier<Boolean> booleanSupplier) {
        return new PatientBooleanBuilder(booleanSupplier);
    }

    /**
     * Patiently retries execution of callable until success (no Exception/Throw) or
     * until specified time have passed.
     * @param callable    The Callable to be evaluated.
     * @param everyMs     Interval (in milliseconds) between each retry.
     * @param untilMs     Maximum time (in milliseconds) after which it stops retrying.
     * @return            Return value of callable (if successful). An exception is thrown otherwise.
     * @param <T>         Return type of callable to be evaluated
     * @see PatientCallable
     */
    public static <T> T retry(Callable<T> callable, int everyMs, long untilMs) {
        return retry(callable)
                .every(everyMs)
                .until(untilMs);
    }

    /**
     * Patiently retries execution of runnable until success (no Exception/Throw) or
     * until specified time have passed.
     * @param runnable    The Runnable to be evaluated.
     * @param everyMs     Interval (in milliseconds) between each retry.
     * @param untilMs     Maximum time (in milliseconds) after which it stops retrying.
     * @see PatientRunnable
     */
    public static void retry(Runnable runnable, int everyMs, long untilMs) {
        retry(runnable)
                .every(everyMs)
                .until(untilMs);
    }

    /**
     * Patiently retries execution of booleanSupplier until success (it returned true) or
     * until specified time have passed.
     * @param booleanSupplier    The boolean Supplier to be evaluated.
     * @param everyMs            Interval (in milliseconds) between each retry.
     * @param untilMs            Maximum time (in milliseconds) after which it stops retrying.
     * @see PatientBoolean
     */
    public static void retry(Supplier<Boolean> booleanSupplier, int everyMs, long untilMs) {
        retry(booleanSupplier)
                .every(everyMs)
                .until(untilMs);
    }
}
