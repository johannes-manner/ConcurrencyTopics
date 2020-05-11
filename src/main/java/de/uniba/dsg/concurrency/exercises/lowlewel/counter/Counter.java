package de.uniba.dsg.concurrency.exercises.lowlewel.counter;

/**
 * Task Description
 * <p>Implement a class <b><i>Counter</i></b>, which is used by many threads concurrently and synchronize its internal state with
 * a synchronization mechanism of your choice, e.g. Semaphore, low level locking etc.
 * The counter should be have a private field <b><i>int value</i></b> and two methods <i><b>void </b><b>increment()</b></i> (increments the
 * internal value by 1) and a method <b><i>int getValue()</i></b> (returns the internal state of the counter).
 * <br></p><p><br></p>
 * As a remark, when assessing your solution. It might be possible that the shown code evaluates for your solution to
 * true and the second test case, we provided to false (the reasons is scheduling, on your machine, also the
 * first test case should be show incorrect result.
 */
public class Counter {
    // implement me
    public Counter(int i) {
    }

    public void increment() {
    }

    public int getValue() {
        return -1;
    }
}
