package de.uniba.dsg.concurrency.exercises.queue;

public class Consumer extends Thread {

    private BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
        super();
        this.queue = queue;
    }

    @Override
    public void run() {
        boolean finished = false;
        while (!finished) {

            try {
                String entry = queue.take();

                if ("DONE".equals(entry)) {
                    finished = true;
                    System.out.println("Consumer received termination message . . .");
                } else {
                    System.out.println("Consuming " + entry);
                }
            } catch (InterruptedException e) {
                // swallow the interruption since the producer always sends a DONE as termination message
                System.out.println("Consumer interrupted . . . ");
            }
        }
    }

}
