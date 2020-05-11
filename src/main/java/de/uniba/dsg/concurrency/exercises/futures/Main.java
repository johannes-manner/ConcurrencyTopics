package de.uniba.dsg.concurrency.exercises.futures;

public class Main {

    public static void main(String[] args) {
        NumberExecutor executor = new NumberExecutor(10);
        System.out.println("Result: " + executor.getSum());
    }

}
