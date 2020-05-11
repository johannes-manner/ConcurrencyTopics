package de.uniba.dsg.concurrency.exercises.lowlewel.counter;

import java.util.ArrayList;
import java.util.List;

public class CounterMain {

    public static void main(String[] args) throws InterruptedException {

// Test Case 1

// create counter
        Counter counter = new Counter(0);

// create threads
        CounterThread t1 = new CounterThread(counter);
        CounterThread t2 = new CounterThread(counter);

// Start threads
        t1.start();
        t2.start();

// Wait for termination
        t1.join();
        t2.join();

// print result
        System.out.println(Thread.currentThread().getName() + ": end value: " + counter.getValue());

// Test Case 2
        Counter c = new Counter(0);
        List<CounterThread> threadList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threadList.add(new CounterThread(c));
        }

        for (int i = 0; i < 10; i++) {
            threadList.get(i).start();
        }

        for (int i = 0; i < 10; i++) {
            threadList.get(i).join();
        }

        System.out.println(Thread.currentThread().getName() + ": end value: " + c.getValue());

// END MAIN
    }

}
