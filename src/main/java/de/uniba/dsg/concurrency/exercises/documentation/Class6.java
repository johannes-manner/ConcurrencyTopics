package de.uniba.dsg.concurrency.exercises.documentation;

public class Class6 {

    private static int x;

    public synchronized int incrementAndGetX() {
        x++;

        return x;
    }

}
