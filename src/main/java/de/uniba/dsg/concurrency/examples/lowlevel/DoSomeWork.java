package de.uniba.dsg.concurrency.examples.lowlevel;

public class DoSomeWork {

    public synchronized void doIt() {
        this.print("Do It");
    }

    public void doItAgain() {
        synchronized (this) {
            this.print("Do it again");
        }
        // not synchronized here - giving control to other threads
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void print(String s) {
        System.out.println("Executing thread: " + Thread.currentThread().getName() + "\t" + s);
    }

    public static void main(String[] args) {
        DoSomeWork work = new DoSomeWork();

        Object mutex = new Object();    // only for testing and understanding :)

        Runnable r = new Runnable() {
            @Override
            public void run() {
                // outside class synchronization - compound action
                synchronized (work) {     // this works properly, since the same object here and for methods doIt and doItAgain is used
                    // you can also assess this when dummy thread is running
                    //synchronized (mutex) {    // this works also  technically in this use case, but does not guarantee the compound action, since
                    // other threads like our dummy thread can interleave between the two calls
                    work.doItAgain();
                    work.doIt();
                }
            }
        };

        Thread doIt = new Thread(r, "1");
        Thread doItAgain = new Thread(r, "2");

        // dummy runnable which also executes the doIt method
        Thread d1 = startDummyThread(work);

        doIt.start();
        doItAgain.start();

        try {
            d1.join();
            doIt.join();
            doItAgain.join();
        } catch (InterruptedException e) {
            // TODO
        }

        work.print("DONE");
    }

    private static Thread startDummyThread(DoSomeWork work) {
        Runnable rr = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    work.doIt();
                    try {
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        };


        Thread d1 = new Thread(rr, "D1");
        d1.start();
        return d1;
    }
}
