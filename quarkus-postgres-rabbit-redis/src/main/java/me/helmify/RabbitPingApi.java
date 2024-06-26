package me.helmify;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Path("/ping")
public class RabbitPingApi {

    @Channel("quotes-out")
    Emitter<String> quoteRequestEmitter;

    @GET
    @Path("/rabbitmq")
    public Map<String, Object> ping() throws Exception {
        String payload = "rabbitmq";

        quoteRequestEmitter.send(payload);
        String poll = QuoteReceiver.QUEUE.poll(10, TimeUnit.SECONDS);

        return Map.of("rabbitmq", payload.equals(poll));
    }
}
