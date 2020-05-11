package de.uniba.dsg.concurrency.examples.highlevel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedListSample {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("PROBLEM:");
        System.out.println(" - - - - ");
        mainProblem();

        System.out.println();
        System.out.println("SOLUTION:");
        System.out.println(" - - - - ");
        mainSolution();
    }

    public static void mainProblem() throws InterruptedException {
        List<String> strings = new ArrayList<>();
        List<String> synchronizedWrapper = Collections.synchronizedList(strings);
        synchronizedWrapper.add("First");

        Runnable a = new Runnable() {
            @Override
            public void run() {
                if (synchronizedWrapper.size() == 1) {
                    try {
                        // is necessary to get interleaving
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // i dont care here
                    }
                    try {
                        System.out.println("I (" + Thread.currentThread().getName() + ") got the item:" + synchronizedWrapper.get(0));
                        synchronizedWrapper.remove(0);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(" - - - A synchronization error occured :(");
                    }
                } else {
                    System.out.println("I (" + Thread.currentThread().getName() + ") no item found :(");
                }
            }
        };

        Runnable b = new Runnable() {
            @Override
            public void run() {
                try {
                    // is necessary to get interleaving
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // i dont care here
                }
                if (synchronizedWrapper.size() == 1) {
                    System.out.println("I (" + Thread.currentThread().getName() + ") got the item:" + synchronizedWrapper.get(0));
                    synchronizedWrapper.remove(0);
                } else {
                    System.out.println("I (" + Thread.currentThread().getName() + ") no item found :(");
                }
            }
        };

        Thread aThread = new Thread(a, "A");
        Thread bThread = new Thread(b, "B");

        aThread.start();
        bThread.start();

        aThread.join();
        bThread.join();

        System.out.println("FINISHED :)");
    }

    private static void mainSolution() throws InterruptedException {

        List<String> strings = new ArrayList<>();
        List<String> synchronizedWrapper = Collections.synchronizedList(strings);
        synchronizedWrapper.add("First");

        Runnable a = new Runnable() {
            @Override
            public void run() {
                // compound action (size(), get(), remove()) - done by us - the developers
                synchronized (synchronizedWrapper) {
                    if (synchronizedWrapper.size() == 1) {
                        try {
                            // is necessary to get interleaving
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            // i dont care here
                        }
                        System.out.println("I (" + Thread.currentThread().getName() + ") got the item:" + synchronizedWrapper.get(0));
                        synchronizedWrapper.remove(0);
                    } else {
                        System.out.println("I (" + Thread.currentThread().getName() + ") no item found :(");
                    }
                }
            }
        };

        Runnable b = new Runnable() {
            @Override
            public void run() {
                try {
                    // is necessary to get interleaving
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // i dont care here
                }
                // compound action (size(), get(), remove()) - done by us - the developers
                synchronized (synchronizedWrapper) {
                    if (synchronizedWrapper.size() == 1) {
                        System.out.println("I (" + Thread.currentThread().getName() + ") got the item:" + synchronizedWrapper.get(0));
                        synchronizedWrapper.remove(0);
                    } else {
                        System.out.println("I (" + Thread.currentThread().getName() + ") no item found :(");
                    }
                }
            }
        };

        Thread aThread = new Thread(a, "A");
        Thread bThread = new Thread(b, "B");

        aThread.start();
        bThread.start();

        aThread.join();
        bThread.join();

        System.out.println("FINISHED :)");
    }

}


