package de.uniba.dsg.concurrency.examples.fundamentals;

public class CallByValue {
    public static void main(String[] args) {
        int x = 1;
        int y = 2;
        System.out.println("x=" + x + ";y=" + y);
        modify(x, y);
        System.out.println("x=" + x + ";y=" + y);
    }

    private static void modify(int x, int y) {
        x = 5;
        y = 10;
        System.out.println("x=" + x + ";y=" + y);
        return;
    }
}