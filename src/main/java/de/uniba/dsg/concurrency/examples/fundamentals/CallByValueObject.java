package de.uniba.dsg.concurrency.examples.fundamentals;

/**
 * As already mentioned in the document, there is NO CallByReference in Java.
 * For sake of simplicity and to distinguish the two concepts and also
 * understand the discussion, we call it call by value object here.
 */
public class CallByValueObject {

    public static void main(String[] args) {
        Student s = new Student();
        System.out.println("name=" + s.getName()); // name=
        modifyStudent(s);
        System.out.println("name=" + s.getName()); // name=Alex
    }

    private static void modifyStudent(Student student) {
        student.setName("Alex");
    }
}

class Student {
    private String name = "";

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Before
class Calculator {
    private double operand1;
    private double operand2;

    public Calculator(double operand1, double operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public double add() {
        return operand1 + operand2;
    }
}