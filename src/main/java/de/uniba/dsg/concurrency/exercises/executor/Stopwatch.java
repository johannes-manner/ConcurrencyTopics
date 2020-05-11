package de.uniba.dsg.concurrency.exercises.executor;

import java.text.NumberFormat;

/**
 * Counts the time between a start and a stop event and calculate as well as
 * print the difference.
 *
 * @author Simon Harrer, Joerg Lenhard
 * @version 1.0
 */
public class Stopwatch {

    private Long start;
    private Long stop;

    public void start() {
        start = System.currentTimeMillis();
    }

    public void stop() {
        if (start == null) {
            throw new IllegalStateException(
                    "start() has to be called before stop()");
        }

        stop = System.currentTimeMillis();
    }

    public long diff() {
        if (start == null || stop == null) {
            throw new IllegalStateException(
                    "start() and stop() have to be called before diff()");
        }

        return stop - start;
    }

    /**
     * @return formatted diff
     */
    public String formattedDiff() {
        double seconds = (double) diff() / 1000;

        return NumberFormat.getNumberInstance().format(seconds) + "s";
    }

    public String toString() {
        return "Duration: " + formattedDiff();
    }

    /**
     * Prints out the current diff.
     */
    public void print() {
        System.out.println(this);
    }

}
