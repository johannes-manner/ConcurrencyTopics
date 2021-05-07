package de.uniba.dsg.concurrency.examples.jaxrs.client;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

public class JaxRsClient {

    private static final String baseUri = "http://localhost:9999/v1";

    private final Client client;

    public JaxRsClient() {
        this.client = ClientBuilder.newClient();
    }

    public void concurrentRequest(int numberOfRequests, String path) {
        // having enough threads before hitting the endpoint
        ExecutorService exec = Executors.newFixedThreadPool(numberOfRequests);
        List<Future<Integer>> futures = new ArrayList<>();

        for(int i = 0 ; i < numberOfRequests ; i++ ){
            // concurrent invocation
            futures.add(exec.submit(() -> this.sendGetRequest(path)));
        }

        int noOfFailedRequests = 0;
        for (int i = 0 ; i < numberOfRequests ; i++) {
            try {
                futures.get(i).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                System.err.println(e.getMessage());
                noOfFailedRequests++;
            }
        }

        System.out.println("Number of failed requests - " + noOfFailedRequests);

        this.shutdownAndAwaitTermination(exec);
    }

    public Integer sendGetRequest(String path) throws ProcessingException {
        WebTarget target = client.target(JaxRsClient.baseUri + path);
        return target.request().get(Integer.class);
    }

    // copied from the api docu
    private void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }
}
