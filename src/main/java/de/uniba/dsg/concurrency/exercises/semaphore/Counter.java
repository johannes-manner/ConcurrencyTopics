package de.uniba.dsg.concurrency.exercises.semaphore;

public class Counter {

    private int value;
    private volatile boolean corrupted = false;

    public void increment() {
        value++;
    }

    public int getValue() {
        return value;
    }

    public void computationCorrupted() {
        this.corrupted = true;
    }

    public boolean isCorrupted() {
        return this.corrupted;
    }
}
