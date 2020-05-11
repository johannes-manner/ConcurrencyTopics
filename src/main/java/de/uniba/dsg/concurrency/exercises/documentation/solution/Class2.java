package de.uniba.dsg.concurrency.exercises.documentation.solution;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * ThreadSafe
 */
@ThreadSafe
public class Class2 {

    @GuardedBy(value = "this")
    private int x;

    public Class2(int x) {
        super();
        this.x = x;
    }

    public synchronized int getX() {
        return x;
    }

    public synchronized void setX(int x) {
        this.x = x;
    }

}
