package org.acme;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.bson.Document;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Map;

@Path("/ping")
public class MongodbPingController {

    @Inject
    MongoClient mongoClient;

    @ConfigProperty(name = "quarkus.mongodb.database")
    String database;



    @GET
    @Path("/mongodb")
    public Map<String, Object> hello() {
        MongoDatabase db = mongoClient.getDatabase(database);
        String collection = "test";
        db.createCollection(collection);
        String name = "mongodb";
        db.getCollection(collection).insertOne(new Document().append("name", name));
        Document document = db.getCollection(collection).find().first();

        return Map.of("mongodb", name.equals(document.get("name")));
    }
}
