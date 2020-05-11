package de.uniba.dsg.concurrency.examples.fundamentals;

public class FootballApplication {

    public static void main(String[] args) {

        // Stack and Heap solution
        int index = 0;

        Player[] cardIndex = new Player[5];
        cardIndex[0] = new Player("Olli", 45, 0);
        index++;

        Player scorer = new Player("Miroslav", 36, 16);
        cardIndex[1] = scorer;
        index++;

    }
}

class Player {

    private String name;
    private int age;
    private int wmGoals;

    public Player(String name, int age, int wmGoals) {

        // input validation and error resolution
        if (age < 0 || age > 120) {
            // exception would be a good choice
            // for sake of simplicity only print in error stream
            System.err.println("Illegal Argument age: " + age);
        }

        // input validation and error resolution
        if (wmGoals < 0) {
            // exception would be a good choice
            // for sake of simplicity only print in error stream
            System.err.println("Illegal Argument wmGoals: " + wmGoals);
        }

        // input validation and error resolution
        if (name == null) {
            // exception would be a good choice
            // for sake of simplicity only print in error stream
            System.err.println("Illegal Argument name: " + name);
        }

        this.name = name;
        this.age = age;
        this.wmGoals = wmGoals;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getWmGoals() {
        return wmGoals;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", wmGoals=" + wmGoals +
                '}';
    }
}
