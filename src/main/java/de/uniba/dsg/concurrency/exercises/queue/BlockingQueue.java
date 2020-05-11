package de.uniba.dsg.concurrency.exercises.queue;

public interface BlockingQueue<E> {

    E take() throws InterruptedException;

    void put(E entry) throws InterruptedException;

}
