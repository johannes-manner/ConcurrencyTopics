package de.uniba.dsg.concurrency.examples.interruption;

import java.time.LocalDateTime;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * You will see in the console output, that all invocations to blocking methods (sleep and take) (when the
 * interruption status is true), will immediately return and throw the exception.
 */
public class PreserveStatus {

    public static void main(String[] args) throws InterruptedException {

        MyPreserveStatusRunnable r = new MyPreserveStatusRunnable();

        Thread t = new Thread(r, "preserver");
        t.start();

        // To see some changes
        Thread.sleep(3500);

        // interrupt the thread and see the exception handling :)
        t.interrupt();

        // join it and exit the JVM
        t.join();
    }
}

/**
 * All statements are printed to System.err otherwise the ordering of System.out and System.err is not preserved
 * and the aspect we want to discuss cannot be easily understood.
 */
class MyPreserveStatusRunnable implements Runnable {

    // only for demonstration purposes. The discussion is included in the high level sections.
    private final BlockingQueue<String> cleanUpQueue = new ArrayBlockingQueue<>(1);

    /**
     * We have two or more methods in the control flow, where an InterruptedException is thrown.
     * If the first one (heavy computation) takes a really long time and gets interrupted, it has to reset the
     * interruption status, otherwise the clean up method executes normally and probably will never terminate
     * since it does not recognize the interrupt.
     * (So try it out by uncommenting the Thread.currentThread().interrupt() line)
     */
    @Override

    public void run() {
        heavyComputation();
        cleanUp();
    }

    /**
     * Just some clean-up stuff.
     */
    private void cleanUp() {
        try {
            System.err.println("Now we do some blocking clean up stuff");
            cleanUpQueue.take();
        } catch (InterruptedException e) {
            System.err.println("Can't execute the clean up completely " + LocalDateTime.now());
            Thread.currentThread().interrupt();
        }
    }

    public int heavyComputation() {
        for (int i = 0; i < 10; i++) {
            if (takeANap(1000))
                System.err.println("made a nap");
        }
        return 42;
    }

    private boolean takeANap(int millis) {
        try {
            Thread.sleep(1000);
            return true;
        } catch (InterruptedException e) {
            System.err.println("Preserving the state " + LocalDateTime.now());
            // preserving the status for further blocking operations
            Thread.currentThread().interrupt();
            return false;
        }
    }
}
