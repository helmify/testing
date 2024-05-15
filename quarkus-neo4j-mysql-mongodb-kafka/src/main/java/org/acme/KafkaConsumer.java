package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@ApplicationScoped
public class KafkaConsumer {

    public static final BlockingQueue<String> KAFKA_QUEUE = new ArrayBlockingQueue<>(1);

    @Incoming("requests")
    public void onMessage(String message) {
        KAFKA_QUEUE.add(message);
    }

}
