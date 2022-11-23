package patiently;

import java.util.Optional;

public abstract class Patience<T> {
    private final int retries = 10;

    protected abstract Optional<T> _test();

    public Optional<T> test() {
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
