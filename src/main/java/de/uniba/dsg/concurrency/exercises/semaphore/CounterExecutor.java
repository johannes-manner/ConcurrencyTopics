package de.uniba.dsg.concurrency.exercises.semaphore;

import java.util.LinkedList;
import java.util.List;

public class CounterExecutor {

    private Counter counter = new Counter();
    private List<Thread> threads = new LinkedList<>();
    private BooleanSemaphore semaphore;

    public CounterExecutor(BooleanSemaphore semaphore) {
        this.semaphore = semaphore;
    }

    public void count() {
        createThreads();
        startThreads();
        waitUntilComputationFinished();
        printResult();
    }

    private void createThreads() {
        // create threads
        for (int i = 0; i < 5; i++) {
            threads.add(new CounterThread(counter, semaphore));
        }
    }

    private void startThreads() {
        // start threads
        for (Thread thread : threads) {
            thread.start();
        }
    }

    private void waitUntilComputationFinished() {
        // join on threads
        for (Thread thread : threads) {
            while (thread.isAlive()) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    // termination policy
                    for (Thread toExitThread : threads) {
                        toExitThread.interrupt();
                    }
                }
            }
        }
    }

    private void printResult() {
        if (counter.isCorrupted()) {
            System.err.println("The computation was corrupted! Final count: " + counter.getValue());
        } else {
            System.out.println("Final count (should be 5.000): " + counter.getValue());
        }
    }

}
