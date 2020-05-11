package de.uniba.dsg.concurrency.exercises.queue;

public class SimpleProducerConsumerMain {

    public static void main(String[] args) throws InterruptedException {
        // create
        BlockingQueue<String> queue = new SimpleBlockingQueue<>();
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        // start
        consumer.start();
        producer.start();

        Thread.sleep((int) (Math.random() * 2000));
        // use an interrupt to shutdown the producer/consumer pair
        producer.interrupt();

        // exit main Thread
    }

}
