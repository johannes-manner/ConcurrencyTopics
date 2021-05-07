package de.uniba.dsg.concurrency.examples.jaxrs.server;

import java.io.IOException;
import java.net.URI;
import java.util.Properties;

import com.sun.net.httpserver.HttpServer;
import jakarta.ws.rs.core.UriBuilder;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class JaxRsServer {
    private static Properties properties = Configuration.loadProperties();

    public static void main(String[] args) throws IOException {
        String serverUri = properties.getProperty("serverUri");

        URI baseUri = UriBuilder.fromUri(serverUri).build();
        ResourceConfig config = ResourceConfig.forApplicationClass(ExamplesApi.class);
        HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, config);
        System.out.println("Server ready to serve your JAX-RS requests...");
        System.out.println("Press any key to exit...");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(1);
    }
}
