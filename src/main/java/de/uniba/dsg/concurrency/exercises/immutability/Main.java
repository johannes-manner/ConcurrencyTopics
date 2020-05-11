package de.uniba.dsg.concurrency.exercises.immutability;

/**
 * Normally, each class get's its own file, but we want to use the pythontutor
 * to visualize the memory model result and see some breaches of the immutability
 * property.
 * Therefore all needed classes are included in this single file :)
 * <p>
 * Pythontutor is available here: http://pythontutor.com/visualize.html
 * (You can also clone the repo on github and host the tutor on your machine
 * Look into the PDF to configure it properly, so you can see all objects and references.
 */

/**
 * Task description
 * Make <i>Student</i> and <i>Group</i> class immutable.
 */
public class Main {
    public static void main(String[] args) {
        Group group = new Group("group1");
        Student[] members = new Student[3];
        members[0] = new Student("John Doe", new Birthday(1, 1, 2000));
        members[1] = new Student("Jane Doe", new Birthday(14, 2, 2000));
        members[2] = new Student("Max Mustermann", new Birthday(1, 4, 2000));
        group.changeMembers(members);
        System.out.println(group);
    }
}

final class Birthday {
    private final int day, month, year;

    public Birthday(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Birthday{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }
}

class Student {
    private String name;
    private Birthday birthday;

    public Student(String name, Birthday birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public Birthday getBirthday() {
        return birthday;
    }

    public Student setBirthday(Birthday birthday) {
        this.birthday = birthday;
        return this;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}

class Group {
    private String name;
    private Student[] members;

    public Group(String name) {
        this.name = name;
    }

    public Group(String name, Student[] members) {
        this.name = name;
        this.members = members;
    }

    public Group changeMembers(Student[] members) {
        this.members = members;
        return this;
    }

    public Student[] getMembers() {
        return this.members;
    }

    @Override
    public String toString() {
        String memberString = "";
        for (int i = 0; i < members.length; i++) {
            memberString += "\t" + members[i] + "\n";
        }
        return "Group{ \n" +
                "name='" + name + '\'' +
                ",\nmembers=\n" + memberString +
                '}';
    }
}
