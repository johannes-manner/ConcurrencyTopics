package de.uniba.dsg.concurrency.exercises.lowlewel.counter;

public class CounterThread extends Thread {

    private Counter counter;

    public CounterThread(Counter counter) {
        this.counter = counter;
    }

    public void run() {
        // increment variable
        for (int i = 0; i < 1000; i++) {
            counter.increment();
        }
    }
}
