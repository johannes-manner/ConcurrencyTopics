package de.uniba.dsg.concurrency.exercises.documentation.solution;

import net.jcip.annotations.NotThreadSafe;

import java.util.List;

/**
 * NotThreadSafe
 * <p>
 * See the discussion about thread safety and the deep copy of objects.
 * The access to the list is thread safe (synchronized) but the reference
 * is not secured (the caller of the constructor or the setter has the same
 * reference, where they can alter the underlying data). Also the getX is
 * problematic since you share a reference to the outside world...
 */
@NotThreadSafe
public class Class5 {

    private List<String> x;

    public Class5(List<String> x) {
        super();
        this.x = x;
    }

    public synchronized List<String> getX() {
        return x;
    }

    public synchronized void setX(List<String> x) {
        this.x = x;
    }

}
