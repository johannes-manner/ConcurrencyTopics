package de.uniba.dsg.concurrency.examples.threads;

/**
 * Currently we do NOT care about interrupted exception and
 * how to deal with them in detail.
 * <p>
 * See later examples and sections for an extensive discussion.
 */
public class Threading {

    public static void main(String[] args) throws InterruptedException {

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    printIt(") waited for a long time and terminate now!");
                } catch (InterruptedException e) {
                    printIt(") was interrupted and terminate now!");
                }
            }
        };

        // thread object creation, like every other object by calling the constructor
        Thread a = new Thread(r, "A");
        Thread b = new Thread(r, "B");

        // starting the threads
        a.start();
        b.start();

        // joining threads
        a.join();
        b.join();

        // exit main thread
        printIt(") am the master and terminate now!");
    }

    private static void printIt(String s) {
        System.out.println("I (" + Thread.currentThread().getName() + s);
    }
}
