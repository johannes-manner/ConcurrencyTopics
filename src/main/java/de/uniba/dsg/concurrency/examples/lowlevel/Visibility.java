package de.uniba.dsg.concurrency.examples.lowlevel;

import java.util.HashMap;
import java.util.Map;

public class Visibility {

    public volatile boolean breakLoop;
    public boolean ready = false;
    public int number = 0;

    public Thread createChanger() {
        Runnable changer = new Runnable() {
            @Override
            public void run() {
                breakLoop = true;   //1
                ready = true;       //2
                number = 42;        //3
                System.out.println("Changer exits");
            }
        };

        Thread t = new Thread(changer, "Changer");
        t.start();
        return t;
    }

    private static final Map<String, Integer> resultDistribution = new HashMap<>();

    public Thread createReader() {
        Runnable reader = new Runnable() {
            @Override
            public void run() {
                while (!breakLoop) {
                    // spin waiting
                }

                boolean tempReady = ready;
                int tempNumber = number;
                String key = "" + ready + " - " + number;

                if (resultDistribution.containsKey(key)) {
                    resultDistribution.put(key, resultDistribution.get(key) + 1);
                } else {
                    resultDistribution.put(key, 1);
                }
            }
        };

        Thread s = new Thread(reader, "Reader");
        s.start();
        return s;
    }

    public static void main(String[] args) throws InterruptedException {
        int noOfIterations = 10_000;
        for (int i = 0; i < noOfIterations; i++) {
            Visibility v = new Visibility();
            Thread reader = v.createReader();
            Thread changer = v.createChanger();

            changer.join();
            reader.join();

            printSummary(i);
        }
    }

    private static void printSummary(int i) {
        System.out.println("---Result Distribution---");
        System.out.println("Key\t\t\tNo of iterations");
        for (String key : resultDistribution.keySet()) {
            System.out.println(key + "\t" + resultDistribution.get(key));
        }
        System.out.println("");
    }
}
