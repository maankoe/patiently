package patiently;

import patiently.builder.PatientCallableBuilder;
import patiently.builder.PatientBooleanBuilder;
import patiently.builder.PatientRunnableBuilder;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class Patiently {
    /**
     * Creates a patient builder for a Callable (e.g., Assertions.assertThat in assertj)
     * @param task    The Callable to be evaluated
     * @return            Builder for PatientCallable
     * @param <T>         Return type of the task to be evaluated
     * @see PatientCallableBuilder
     * @see PatientCallable
     */
    public static <T> PatientCallableBuilder<T> retry(Callable<T> task) {
        return new PatientCallableBuilder<>(task);
    }

    /**
     * Creates a patient builder for a Runnable (e.g., Mockito.verify in mockito).
     * @param task    The Runnable to be evaluated.
     * @return            Builder for PatientRunnable.
     * @see PatientRunnableBuilder
     * @see PatientRunnable
     */
    public static <T> PatientRunnableBuilder retry(Runnable task) {
        return new PatientRunnableBuilder(task);
    }

    /**
     * Creates a patient builder for a boolean Supplier (e.g., a general boolean evaluation).
     * @param task    The boolean Supplier to be evaluated.
     * @return                   Builder for PatientBoolean.
     * @see PatientBooleanBuilder
     * @see PatientBoolean
     */
    public static <T> PatientBooleanBuilder retry(Supplier<Boolean> task) {
        return new PatientBooleanBuilder(task);
    }

    /**
     * Patiently retries execution of task until success (no Exception/Throw) or
     * until specified time have passed.
     * @param task    The Callable to be evaluated.
     * @param everyMs     Interval (in milliseconds) between each retry.
     * @param untilMs     Maximum time (in milliseconds) after which it stops retrying.
     * @return            Return value of task (if successful). An exception is thrown otherwise.
     * @param <T>         Return type of task to be evaluated
     * @see PatientCallable
     */
    public static <T> T retry(Callable<T> task, int everyMs, long untilMs) {
        return retry(task)
                .every(everyMs)
                .until(untilMs);
    }

    /**
     * Patiently retries execution of task until success (no Exception/Throw) or
     * until specified time have passed.
     * @param task    The Runnable to be evaluated.
     * @param everyMs     Interval (in milliseconds) between each retry.
     * @param untilMs     Maximum time (in milliseconds) after which it stops retrying.
     * @see PatientRunnable
     */
    public static void retry(Runnable task, int everyMs, long untilMs) {
        retry(task)
                .every(everyMs)
                .until(untilMs);
    }

    /**
     * Patiently retries execution of task until success (it returned true) or
     * until specified time have passed.
     * @param task    The boolean Supplier to be evaluated.
     * @param everyMs            Interval (in milliseconds) between each retry.
     * @param untilMs            Maximum time (in milliseconds) after which it stops retrying.
     * @see PatientBoolean
     */
    public static void retry(Supplier<Boolean> task, int everyMs, long untilMs) {
        retry(task)
                .every(everyMs)
                .until(untilMs);
    }
}
