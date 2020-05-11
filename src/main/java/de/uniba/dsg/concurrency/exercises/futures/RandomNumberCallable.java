package de.uniba.dsg.concurrency.exercises.futures;

import java.util.concurrent.Callable;

public class RandomNumberCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        //TODO: create a random number, sleep for random number millis and then return millis
        //TODO: when you got interrupted during sleeping, return immediately
        return null;
    }

}
