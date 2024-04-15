package me.helmify;

import io.agroal.api.AgroalDataSource;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.sql.Connection;
import java.util.Map;
import java.util.UUID;

@Path("/ping")
public class RedisPingApi {

    @Inject
    RedisDataSource redisDataSource;

    @GET
    @Path("/redis")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> ping() throws Exception{
        String key = UUID.randomUUID().toString();

        redisDataSource.set(String.class).sadd(key, "1");

        return Map.of("redis", "1".equals(redisDataSource.set(String.class).spop(key)));
    }
}
