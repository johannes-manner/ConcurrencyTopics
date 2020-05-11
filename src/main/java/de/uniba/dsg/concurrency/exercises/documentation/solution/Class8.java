package de.uniba.dsg.concurrency.exercises.documentation.solution;

import net.jcip.annotations.NotThreadSafe;

/**
 * NotThreadSafe
 * <p>
 * Same reason as with class5.
 * Reference to the int array x is exposed.
 */
@NotThreadSafe
public class Class8 {

    private final int[] x;

    public Class8(int[] x) {
        super();
        this.x = x;
    }

    public int[] getX() {
        return x;
    }

}
