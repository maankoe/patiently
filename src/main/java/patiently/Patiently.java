package patiently;

import patiently.builder.PatientBuilder;
import patiently.builder.PatientAssertBuilder;
import patiently.builder.PatientVerifyBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Patiently {

    public static <T> PatientAssertBuilder<T> recheck(Callable<T> test) {
        return new PatientBuilder().recheck(test);
    }

    public static PatientVerifyBuilder recheck(Runnable test) {
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
