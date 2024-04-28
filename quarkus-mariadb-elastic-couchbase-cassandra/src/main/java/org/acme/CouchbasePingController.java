package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.Map;

@Path("/ping")
public class CouchbasePingController {

    @GET
    @Path("/couchbase")
    public Map<String, Object> hello() {
        return Map.of("couchbase", false);
    }
}