package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.Map;

@Path("/ping")
public class MariadbPingController {

    @GET
    @Path("/mariadb")
    public Map<String, Object> hello() {
        return Map.of("mariadb", false);
    }
}
