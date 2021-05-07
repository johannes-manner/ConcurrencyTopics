package de.uniba.dsg.concurrency.examples.jaxrs.client;

import jakarta.ws.rs.ProcessingException;

public class ClientMain {

    public static void main(String[] args) {

        JaxRsClient client = new JaxRsClient();
        try {
            client.concurrentRequest(1000, "/counter/count");
            System.out.println("Default behavior - one instance per call: " + client.sendGetRequest("/counter/result"));

            client.concurrentRequest(3000, "/singleton/count");
            System.out.println("Singleton behavior - single resource instance per application: " + client.sendGetRequest("/singleton/result"));
        } catch (ProcessingException e) {
            System.err.println("Server not available - Start it!");
        }
    }
}
