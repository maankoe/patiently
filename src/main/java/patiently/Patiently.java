package patiently;

import patiently.builder.PatientBuilder;
import patiently.builder.PatientAssertBuilder;
import patiently.builder.PatientVerifyBuilder;

import java.util.concurrent.Callable;

public class Patiently {

    public static <T> PatientAssertBuilder<T> waitFor(Callable<T> test) {
        return new PatientBuilder().waitFor(test);
    }

    public static PatientVerifyBuilder waitFor(Runnable test) {
        return new PatientBuilder().waitFor(test);
    }

    public static <T> T test(Callable<T> test, int maxRetries, long pauseMs) {
        return waitFor(test)
                .retrying(maxRetries)
                .every(pauseMs)
                .test();
    }

    public static void test(Runnable test, int maxRetries, long pauseMs) {
        waitFor(test)
                .retrying(maxRetries)
                .every(pauseMs)
                .test();
    }

}
