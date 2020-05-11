package de.uniba.dsg.concurrency.examples.interruption;

public class DirectPropagation {

    public static void main(String[] args) throws InterruptedException {

        MyPropagationRunnable r = new MyPropagationRunnable();

        Thread t = new Thread(r, "propagator");
        t.start();

        // To see some changes, InterruptedException is also propagated (results in a termination of the JVM)
        Thread.sleep(1500);

        // interrupt the thread and see the exception handling :)
        t.interrupt();

        // join it and exit the JVM
        t.join();
    }
}

class MyPropagationRunnable implements Runnable {

    /**
     * Since run is not able to throw an InterruptedException, it is the best
     * location to handle it, so that the thread terminates properly!
     */
    @Override
    public void run() {
        try {
            heavyComputation();
        } catch (InterruptedException e) {
            System.err.println(Thread.currentThread().getName() + " got interrupted. Terminates now!");
        }
    }

    /**
     * Throws the exception to the caller
     *
     * @return the answer to all questions
     * @throws InterruptedException
     */
    public int heavyComputation() throws InterruptedException {
        for (int i = 0; i < 100_000; i++) {
            takeANap();
            System.out.println("made a nap");
        }
        return 42;
    }

    /**
     * Throws the exception to the caller
     *
     * @throws InterruptedException
     */
    private void takeANap() throws InterruptedException {
        Thread.sleep(100);
    }
}