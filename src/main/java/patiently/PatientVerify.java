package patiently;

public class PatientVerify extends Patience<Void> {
    private final Runnable test;

    public PatientVerify(Runnable test) {
        this.test = test;
    }

    public Void _test() {
        test.run();
        return null;
    }
}
