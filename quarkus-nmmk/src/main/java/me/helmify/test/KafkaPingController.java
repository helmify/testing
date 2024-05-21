package me.helmify.test;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Path("/ping")
public class KafkaPingController {

    @Channel("word-requests")
    Emitter<String> emitter;

    @GET
    @Path("/kafka")
    public Map<String, Object> ping() throws Exception {
        String name = "kafka";
        emitter.send(name);
        String polled = KafkaConsumer.KAFKA_QUEUE.poll(10, TimeUnit.SECONDS);
        return Map.of("kafka", name.equals(polled));
    }
}
