package model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author bubble
 * @since 2024/7/16
 */
public class MinuteCounter {
    private static final int MASK = 0x7FFFFFFF;
    private final AtomicInteger atom;

    public MinuteCounter() {
        atom = new AtomicInteger(0);
    }

    public final int incrementAndGet() {
        return atom.incrementAndGet() & MASK;
    }

    public int get() {
        return atom.get() & MASK;
    }

    public void set(int newValue) {
        atom.set(newValue & MASK);
    }
}

