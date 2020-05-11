package de.uniba.dsg.concurrency.examples.highlevel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorSample {

    /**
     * Copied from: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/concurrent/ExecutorService.html
     * And slightly modified :)
     *
     * @param pool
     */
    private static void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(5, TimeUnit.SECONDS)) {
                // a list of tasks which were already submitted but not executed yet
                List<Runnable> runnableList = pool.shutdownNow(); // Cancel currently executing tasks
                System.out.println(runnableList);
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(5, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }

    private static void handleExceptions(List<ExecutionException> exceptionList) {
        for (ExecutionException e : exceptionList) {
            // Log into error log
            System.err.println("Error when executing callable: cause: " + e.getCause());
        }
    }

    public static void main(String[] args) {
        // at least 11 threads are needed since 10 are blocked when waiting (DoSomethingCriticalRunnable)
//        ExecutorService executor = Executors.newFixedThreadPool(11);
        ExecutorService executor = Executors.newCachedThreadPool();

        List<Future> collectResults = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            collectResults.add(executor.submit(new ShareResultCallable()));
            executor.execute(new DoSomethingRunnable());
            executor.execute(new DoSomethingCriticalRunnable());
        }

        List<ExecutionException> exceptionList = new ArrayList<>();
        for (Future<Integer> f : collectResults) {
            // get result
            try {
                System.out.println("Callable return value: " + f.get());
            } catch (InterruptedException e) {
                // restore the flag, if a succeeding method also blocks
                Thread.currentThread().interrupt();
                // exit the loop
                break;
            } catch (ExecutionException e) {
                // store all execution exceptions which happened during execution of the callables
                exceptionList.add(e);
            }
        }

        handleExceptions(exceptionList);

        ExecutorSample.shutdownAndAwaitTermination(executor);
    }
}

class ShareResultCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int result = 0;
        for (int i = 0; i < 500; i++) {
            result += 1;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                // I swallow this interruption here, since I have
                // an important task to do and the task ends in
                // round about 5 seconds :)
            }
        }

        if (Math.random() > 0.5) {
            throw new IllegalArgumentException("Such a lucky punch");
        }
        System.out.println("ShareResultCallable returns " + result);
        return result;
    }
}

class DoSomethingRunnable implements Runnable {
    @Override
    public void run() {
        int result = 0;
        for (int i = 0; i < 1_000; i++) {
            result += 1;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                // I swallow this interruption here, since I have
                // an important task to do and the task ends in
                // round about 1 seconds :)
            }
        }
        System.out.println("Finished DoSomethingRunnable with " + result);
    }
}

class DoSomethingCriticalRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Entering DoSomethingCriticalRunnable");
        while (true) {
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                // luckily I DO care about the exception,
                // otherwise I would be Sleeping Beauty but never awake

                // since I know that there is no further action here, I do not need to reset the interruption flag
                System.out.println(Thread.currentThread().getName() + " terminates");
                return;
            }
        }
    }
}