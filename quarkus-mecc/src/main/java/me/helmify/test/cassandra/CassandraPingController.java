package me.helmify.test.cassandra;

import com.datastax.oss.quarkus.runtime.api.session.QuarkusCqlSession;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.Map;

@Path("/ping")
public class CassandraPingController {

    @Inject
    CassandraDao dao;

    @Inject
    QuarkusCqlSession session;

    @GET
    @Path("/cassandra")
    public Map<String, Object> ping() {

        session.execute("CREATE TABLE IF NOT EXISTS cassandra_entity (name text PRIMARY KEY)");

        String cassandra = "cassandra";
        CassandraEntity entity = new CassandraEntity(cassandra);
        dao.save(entity);
        CassandraEntity one = dao.findAll().one();
        return Map.of("cassandra", one != null && cassandra.equals(one.getName()));
    }
}
