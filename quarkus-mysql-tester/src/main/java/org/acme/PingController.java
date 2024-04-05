package org.acme;

import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Map;

@ApplicationScoped
public class PingController {

    @Inject
    AgroalDataSource defaultDataSource;

    @GET
    @Path("/ping")
    public Map<String, Object> ping() throws Exception {
        Connection connection = defaultDataSource.getConnection();
        connection.createStatement().execute("CREATE TABLE IF NOT EXISTS test (id SERIAL PRIMARY KEY, name TEXT)");
        connection.createStatement().execute("insert into test (name) values ('test')");
        Statement statement = connection.createStatement();
        boolean next = statement.executeQuery("SELECT * FROM test where name = 'test'").next();
        return Map.of("mysql", next);
    }

}
