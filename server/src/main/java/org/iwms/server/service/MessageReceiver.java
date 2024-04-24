package org.iwms.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author leung
 */
@Component
public class MessageReceiver {

    static final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    @RabbitListener(queues = "my-queue")
    public void receiveMessage(String message) {
        logger.info("Received message :{} ,from {}", "my-queue", message);
    }
}
