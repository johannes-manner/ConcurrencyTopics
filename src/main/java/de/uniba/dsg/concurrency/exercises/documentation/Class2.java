package de.uniba.dsg.concurrency.exercises.documentation;

public class Class2 {

    private int x;

    public Class2(int x) {
        super();
        this.x = x;
    }

    public synchronized int getX() {
        return x;
    }

    public synchronized void setX(int x) {
        this.x = x;
    }

}
