package de.uniba.dsg.concurrency.exercises.queue;


public class SimpleBlockingQueue<E> implements BlockingQueue<E> {

    private E entry;

    // think about the waiting condition, when a thread has to wait
    @Override
    public E take() throws InterruptedException {
        return null;
    }

    // think about the waiting condition, when a thread has to wait
    @Override
    public void put(E entry) throws InterruptedException {

    }

}
