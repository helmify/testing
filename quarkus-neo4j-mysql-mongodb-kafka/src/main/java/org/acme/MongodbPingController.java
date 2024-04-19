package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.Map;

@Path("/ping")
public class MongodbPingController {

    @GET
    @Path("/mongodb")
    public Map<String, Object> hello() {
        return Map.of("mongodb", false);
    }
}
