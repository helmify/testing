package com.example;

import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.quarkus.runtime.api.session.QuarkusCqlSession;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;

@Path("/fruits")
public class ExampleResource {

    @Inject
    FruitService fruitService;

    @Inject
    QuarkusCqlSession session;

    @ConfigProperty(name = "quarkus.cassandra.keyspace")
    String keyspace;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<?> fruits() {

        ResultSet execute = session.execute(String.format("CREATE TABLE IF NOT EXISTS %s.fruit(name text PRIMARY KEY, description text)", keyspace));
        if(execute.wasApplied()) {
            System.out.println("Table created");
        }

        Fruit fruit = new Fruit();
        fruit.setName("apple");
        fruit.setDescription("red and delicious");
        fruitService.save(fruit);
        return fruitService.getAll();
    }
}
