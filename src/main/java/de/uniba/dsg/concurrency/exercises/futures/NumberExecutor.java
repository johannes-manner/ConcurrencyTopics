package de.uniba.dsg.concurrency.exercises.futures;


public class NumberExecutor {

    private final int numberOfTasks;

    public NumberExecutor(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }

    public int getSum() {
        // TODO: create numberOfTasks RandomNumberCallables and an executor service of your choice

        // TODO: Wait for the result of all RandomNumberCallables, sum the return values up and terminate properly
        return 0;
    }

}
