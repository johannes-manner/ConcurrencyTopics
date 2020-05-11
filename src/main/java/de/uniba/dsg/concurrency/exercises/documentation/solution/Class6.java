package de.uniba.dsg.concurrency.exercises.documentation.solution;

import net.jcip.annotations.NotThreadSafe;

/**
 * NotThreadSafe <br/>
 * <p>
 * The member of the class <i>x</i> is static.
 * The synchronized method is not static and therefore synchronizes on the specific object and not the class itself.
 * </p>
 * <p>
 * For an example, we have two threads which concurrently incrementing x.
 * So they are perfectly synchronized, since each thread synchronizes on its self-reference, so they can concurrently
 * execute this method (each thread has its self-reference and therefore two lock objects secure x, since x is static).
 * </p><p>
 * So a thread safe solution would like like the following:
 *
 * <pre>{@code
 * @ThreadSafe
 * public class Class6 {
 *
 *     @GuardedBy(value="Class6")
 *     private static int x;
 *
 *     public int incrementAndGetX() {
 *         synchronized (Class6.class) {
 *             x++;
 *
 *             return x;
 *         }
 *     }
 *
 * }
 * }</pre>
 */
@NotThreadSafe
public class Class6 {

    private static int x;

    public synchronized int incrementAndGetX() {
        x++;

        return x;
    }

}