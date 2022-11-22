package patiently;

public abstract class Patience<T> {
    private final int retries = 10;

    protected abstract T _test();

    public T test() {
        for (int i=0;i<this.retries;i++) {
            try {
                return this._test();
            } catch (Throwable ignored) {}
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return this._test();
    }
}
