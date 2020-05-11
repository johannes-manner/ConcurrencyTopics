package de.uniba.dsg.concurrency.exercises.documentation.solution;

import net.jcip.annotations.NotThreadSafe;

/**
 * NotThreadSafe
 * <p>
 * The access to x (setX) is not synchronized.
 */
@NotThreadSafe
public class Class1 {

    private int x;

    public Class1(int x) {
        super();
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

}
