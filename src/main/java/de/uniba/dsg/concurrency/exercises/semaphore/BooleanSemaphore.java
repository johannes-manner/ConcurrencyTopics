package de.uniba.dsg.concurrency.exercises.semaphore;

public interface BooleanSemaphore {

    /**
     * acquire lock
     */
    void acquire() throws InterruptedException;

    /**
     * release lock
     */
    void release();

}
