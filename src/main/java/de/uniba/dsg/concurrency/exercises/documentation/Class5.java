package de.uniba.dsg.concurrency.exercises.documentation;

import java.util.List;

public class Class5 {

    private List<String> x;

    public Class5(List<String> x) {
        super();
        this.x = x;
    }

    public synchronized List<String> getX() {
        return x;
    }

    public synchronized void setX(List<String> x) {
        this.x = x;
    }

}
