package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.Map;

@Path("/ping")
public class MysqlPingController {

    @GET
    @Path("/mysql")
    public Map<String, Object> hello() {
        return Map.of("mysql", false);
    }
}
