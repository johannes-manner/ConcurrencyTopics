package de.uniba.dsg.concurrency.exercises.documentation;

public class Class4 {

    private int x;
    private Object lock;

    public Class4(int x) {
        super();
        this.x = x;
    }

    public int getX() {
        synchronized (lock) {
            return x;
        }
    }

    public synchronized void setX(int x) {
        synchronized (lock) {
            this.x = x;
        }
    }

}