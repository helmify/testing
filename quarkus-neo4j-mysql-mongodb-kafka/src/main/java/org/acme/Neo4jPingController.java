package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.jboss.resteasy.reactive.ResponseStatus;
import org.neo4j.driver.Driver;
import org.neo4j.driver.reactive.ReactiveResult;
import org.neo4j.driver.reactive.ReactiveSession;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import java.time.Duration;
import java.util.Map;


@Path("/ping")
public class Neo4jPingController {

    @Inject
    Driver driver;

    @GET
    @Path("/neo4j")
    public Map<String, Object> ping() {

        Neo4jTest test = new Neo4jTest();
        String name = "neo4j";
        test.setName(name);

        // write
        Uni.createFrom().emitter(e -> Multi.createFrom().resource(() -> driver.session(ReactiveSession.class),
                                session -> session.executeWrite(tx -> {
                                    var result = tx.run(
                                            "CREATE (t:Neo4jTest {id: randomUUID(), name: $name}) RETURN t",
                                            Map.of("name", test.name));
                                    return Multi.createFrom().publisher(result).flatMap(ReactiveResult::records);
                                }))
                        .withFinalizer(Neo4jPingController::sessionFinalizer)
                        .subscribe()
                        .asIterable().stream().iterator().next())
                .await().atMost(Duration.ofSeconds(10));



        // read
        String fromDb = Multi.createFrom().resource(() -> driver.session(ReactiveSession.class),
                        session -> session.executeRead(tx -> {
                            var result = tx.run("MATCH (t:Neo4jTest) RETURN t.name as name ORDER BY t.name");
                            return Multi.createFrom().publisher(result).flatMap(ReactiveResult::records);
                        }))
                .withFinalizer(Neo4jPingController::sessionFinalizer)
                .map(record -> record.get("name").asString()).subscribe().asIterable().stream().iterator().next();

        return Map.of("neo4j", name.equals(fromDb));
    }

    public static class Neo4jTest {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    static Uni<Void> sessionFinalizer(ReactiveSession session) {
        return Uni.createFrom().publisher(session.close());
    }
}
