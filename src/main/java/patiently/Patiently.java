package patiently;

import patiently.builder.PatientCallableBuilder;
import patiently.builder.PatientPredicateBuilder;
import patiently.builder.PatientRunnableBuilder;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class Patiently {

    public static <T> PatientCallableBuilder<T> test(Callable<T> test) {
        return new PatientCallableBuilder<>(test);
    }

    public static <T> PatientRunnableBuilder test(Runnable test) {
        return new PatientRunnableBuilder(test);
    }

    public static <T> PatientPredicateBuilder test(Supplier<Boolean> test) {
        return new PatientPredicateBuilder(test);
    }

    public static <T> T test(Callable<T> test, int everyMs, long untilMs) {
        return test(test)
                .everyMs(everyMs)
                .untilMs(untilMs);
    }

    public static void test(Runnable test, int everyMs, long untilMs) {
        test(test)
                .everyMs(everyMs)
                .untilMs(untilMs);
    }
}
