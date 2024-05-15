package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.acme.KafkaConsumer.KAFKA_QUEUE;

@Path("/ping")
public class KafkaPingController {

    @Channel("word-requests")
    Emitter<String> emitter;

    @GET
    @Path("/kafka")
    public Map<String, Object> ping() throws Exception {
        String name = "kafka";
        emitter.send(name);
        String polled = KAFKA_QUEUE.poll(10, TimeUnit.SECONDS);
        return Map.of("kafka", name.equals(polled));
    }
}
