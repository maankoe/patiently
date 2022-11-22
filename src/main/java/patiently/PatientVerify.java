package patiently;

public class PatientVerify {
    private final Runnable test;
    private final int retries = 10;

    public PatientVerify(Runnable test) {
        this.test = test;
    }

    public void test() {
        for (int i=0;i<this.retries;i++) {
            try {
                test.run();
                return;
            } catch (Throwable ignored) {}
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        test.run();
    }
}
