package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.Map;

@Path("/ping")
public class KafkaPingController {

    @GET
    @Path("/kafka")
    public Map<String, Object> hello() {
        return Map.of("kafka", false);
    }
}
