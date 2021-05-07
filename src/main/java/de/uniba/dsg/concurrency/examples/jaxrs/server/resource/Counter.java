package de.uniba.dsg.concurrency.examples.jaxrs.server.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("counter")
public class Counter {

    private int counter;

    @GET
    @Path("count")
    public void count() {
        counter++;
    }

    @GET
    @Path("result")
    public int getCounter() {
        return counter;
    }

    @GET
    @Path("reset")
    public void reset() {
        counter = 0;
    }
}
