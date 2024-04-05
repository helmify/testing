package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@ApplicationScoped
public class QuoteReceiver {

    public static final BlockingQueue<String> QUEUE = new ArrayBlockingQueue<>(1);

    @Incoming("quotes-in")
    public void receive(String quote) {
        QUEUE.add(quote);
    }

}
