package patiently;

import patiently.builder.PatientBuilder;
import patiently.builder.PatientCallableBuilder;
import patiently.builder.PatientPredicateBuilder;
import patiently.builder.PatientRunnableBuilder;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class Patiently {

    public static <T> PatientCallableBuilder<T> recheck(Callable<T> test) {
        return new PatientBuilder().recheck(test);
    }

    public static PatientRunnableBuilder recheck(Runnable test) {
        return new PatientBuilder().recheck(test);
    }

    public static PatientPredicateBuilder recheck(Supplier<Boolean> test) {
        return new PatientBuilder().recheck(test);
    }

    public static PatientBuilder retrying(int maxRetries) {
        return new PatientBuilder().retrying(maxRetries);
    }

    public static PatientBuilder every(long pauseMs) {
        return new PatientBuilder().every(pauseMs);
    }

    public static <T> T test(Callable<T> test, int maxRetries, long pauseMs) {
        return recheck(test)
                .retrying(maxRetries)
                .every(pauseMs)
                .test();
    }

    public static void test(Runnable test, int maxRetries, long pauseMs) {
        recheck(test)
                .retrying(maxRetries)
                .every(pauseMs)
                .test();
    }

}
