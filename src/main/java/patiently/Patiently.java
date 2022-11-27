package patiently;

import patiently.builder.PatientCallableBuilder;
import patiently.builder.PatientPredicateBuilder;
import patiently.builder.PatientRunnableBuilder;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class Patiently {

    public static <T> PatientCallableBuilder<T> retry(Callable<T> test) {
        return new PatientCallableBuilder<>(test);
    }

    public static <T> PatientRunnableBuilder retry(Runnable test) {
        return new PatientRunnableBuilder(test);
    }

    public static <T> PatientPredicateBuilder retry(Supplier<Boolean> test) {
        return new PatientPredicateBuilder(test);
    }

    public static <T> T retry(Callable<T> test, int everyMs, long untilMs) {
        return retry(test)
                .everyMs(everyMs)
                .untilMs(untilMs);
    }

    public static void retry(Runnable test, int everyMs, long untilMs) {
        retry(test)
                .everyMs(everyMs)
                .untilMs(untilMs);
    }
}
