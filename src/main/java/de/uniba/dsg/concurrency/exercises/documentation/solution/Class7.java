package de.uniba.dsg.concurrency.exercises.documentation.solution;

import net.jcip.annotations.ThreadSafe;

/**
 * ThreadSafe
 * <p>
 * The implementation of the class is immutable, but the class itself is not final!
 * If the class is final - the
 */
@ThreadSafe
public class Class7 {

    private final int x;

    public Class7(int x) {
        super();
        this.x = x;
    }

    public int getX() {
        return x;
    }

}
