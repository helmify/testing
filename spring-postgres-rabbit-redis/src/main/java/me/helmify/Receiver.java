package me.helmify;

import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Component
public class Receiver {

    private BlockingQueue<String> messages = new ArrayBlockingQueue<>(1);

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        messages.add(message);
    }

    public BlockingQueue<String> getMessages() {
        return messages;
    }
}
