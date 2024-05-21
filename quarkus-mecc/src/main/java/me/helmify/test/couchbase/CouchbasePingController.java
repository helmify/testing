package me.helmify.test.couchbase;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.Map;

@Path("/ping")
public class CouchbasePingController {

    @GET
    @Path("/couchbase")
    public Map<String, Object> ping() {
        // at this time there is no official Couchbase client for Quarkus
        // and community maintained extensions do not currently work.
        return Map.of("couchbase", false);
    }
}
