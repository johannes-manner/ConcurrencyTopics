package de.uniba.dsg.concurrency.exercises.documentation.solution;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * ThreadSafe
 * <p>
 * Avoid situation like in setX where you use more than a single lock object!
 * These situations as you know are highly deadlock prone.
 */
@ThreadSafe
public class Class4 {

    @GuardedBy(value = "lock")
    private int x;
    private Object lock;

    public Class4(int x) {
        super();
        this.x = x;
    }

    public int getX() {
        synchronized (lock) {
            return x;
        }
    }

    public synchronized void setX(int x) {
        synchronized (lock) {
            this.x = x;
        }
    }

}