package me.helmify.test;

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
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import java.time.Duration;
import java.util.Map;


@Path("/ping")
public class Neo4jPingController {

    @Inject
    SessionFactory sessionFactory;

    @GET
    @Path("/neo4j")
    public Map<String, Object> ping() {

        Neo4jTest test = new Neo4jTest();
        String name = "neo4j";
        test.setName(name);

        Session session = sessionFactory.openSession();
        session.save(test);

        Neo4jTest fromdb = session.loadAll(Neo4jTest.class).iterator().next();

        return Map.of("neo4j", name.equals(fromdb.getName()));
    }

    @NodeEntity
    public static class Neo4jTest {


        @Id
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
