package de.uniba.dsg.concurrency.exercises.semaphore;

import java.util.Set;

public class Main {

    public static void main(String[] args) {
        BooleanSemaphore semaphore = new UnfairBooleanSemaphore();
        CounterExecutor executor = new CounterExecutor(semaphore);
        executor.count();
    }

    public static void interruptMainThread() {
        Set<Thread> threads = Thread.getAllStackTraces().keySet();

        for (Thread t : threads) {
            if ("main".equals(t.getName())) {
                t.interrupt();
            }
        }
    }

}
