package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.Map;

@Path("/ping")
public class Neo4jPingController {

    @GET
    @Path("/neo4j")
    public Map<String, Object> hello() {
        return Map.of("neo4j", false);
    }
}
