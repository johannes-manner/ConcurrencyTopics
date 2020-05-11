package de.uniba.dsg.concurrency.exercises.executor;

public class Main {

    public static void main(String[] args) {

        long runnablesCount = 100;
        long max = 100_000;

        new ExecutorComparison(max, runnablesCount).compare();

    }

}
