package com.example;

import io.agroal.api.AgroalDataSource;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.sql.Connection;
import java.util.Map;

@Path("/ping")
public class PingApi {
    @Inject
    AgroalDataSource ds;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> ping() throws Exception{
        Connection connection = ds.getConnection();
        boolean execute = connection.prepareStatement("select 1").execute();
        return Map.of("postgres", execute);
    }
}
