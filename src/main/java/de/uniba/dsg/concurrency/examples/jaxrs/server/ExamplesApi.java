package de.uniba.dsg.concurrency.examples.jaxrs.server;

import java.util.HashSet;
import java.util.Set;

import de.uniba.dsg.concurrency.examples.jaxrs.server.resource.Counter;
import de.uniba.dsg.concurrency.examples.jaxrs.server.resource.SingletonCounter;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;

@ApplicationPath("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ExamplesApi extends Application {

    // default behavior
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> resources = new HashSet<>();
        resources.add(Counter.class);
        return resources;
    }

    // behavior (explicitly overriden)
    @Override
    public Set<Object> getSingletons() {
        Set<Object> set = new HashSet<>();
        set.add(new SingletonCounter());
        return set;
    }
}
