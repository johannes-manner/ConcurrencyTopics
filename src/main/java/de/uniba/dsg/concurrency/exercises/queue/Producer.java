package de.uniba.dsg.concurrency.exercises.queue;

public class Producer extends Thread {

    private BlockingQueue<String> queue;

    public Producer(BlockingQueue<String> queue) {
        super();
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            try {
                queue.put("WORK PACKAGE #" + i);
                Thread.sleep(1);
            } catch (InterruptedException e) {
                // interruption means breaking the execution in this implementation
                // this is always dependent on the business logic (only an example here)
                System.out.println("Producer interrupted . . .");
                break;
            }
        }

        while (true) {
            try {
                queue.put("DONE");
                System.out.println("Producer sent termination message . . .");
                break;
            } catch (InterruptedException e) {
                // swallow the exception and retry the "DONE" message
                // to also terminate the consumer
            }
        }
    }

}
