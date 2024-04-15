package me.helmify;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.util.ErrorHandler;


public class CustomErrorHandler implements ErrorHandler {
    @Override
    public void handleError(Throwable t) {
        throw new AmqpRejectAndDontRequeueException("Error Handler converted exception to fatal", t);
    }
}
