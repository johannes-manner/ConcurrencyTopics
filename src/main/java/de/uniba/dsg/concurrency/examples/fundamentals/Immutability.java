package de.uniba.dsg.concurrency.examples.fundamentals;

public class Immutability {
}

// Mutable
class Age {
    private int day, month, year;

    // Constructor
    public Age(int day, int month, int year) {
        // ...
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

// Getter and Setter
}

// Immutable
final class ImmutableStudent {          //1
    private final int id;               //2
    // Strings are constant
    // their values cannot be changed
    private final String name;          //2
    private final Age age;              //2

    public ImmutableStudent(int id, String name, Age age) {
        this.name = name;
        this.id = id;
        this.age = new Age(age.getDay(),            //5.1
                age.getMonth(), age.getYear());     //5.1
    }

    // no setters                                   //3
    // 'normal' getters for id and name (final)
    public Age getAge() {
        return new Age(age.getDay(),                //5.2
                age.getMonth(), age.getYear());     //5.2
    }

    public ImmutableStudent hasMarried(String newName) {    //4
        return new ImmutableStudent(this.id, newName, this.age); //4
    }
}
