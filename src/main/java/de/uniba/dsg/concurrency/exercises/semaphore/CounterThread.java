package de.uniba.dsg.concurrency.exercises.semaphore;

public class CounterThread extends Thread {

    private Counter counter;
    private BooleanSemaphore semaphore;

    public CounterThread(Counter counter, BooleanSemaphore semaphore) {
        this.counter = counter;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        // increment 1 million times
        // _ can be used in numbers to structure them. this is a Java 7 feature
        for (int i = 0; i < 1_000; i++) {

            try {
                semaphore.acquire();

                // Start Critical Section
                try {
                    counter.increment();
                    // to show the termination policy when interrupt main thread
                    // artificially introduced to interrupt main thread... :/
                    if (Math.random() < 0.0001)
                        Main.interruptMainThread();
                } finally {
                    // End Critical Section
                    semaphore.release();
                }
            } catch (InterruptedException e) {
                // clean up
                counter.computationCorrupted();
                // allow thread to exit
                return;
            }

        }
    }

}
