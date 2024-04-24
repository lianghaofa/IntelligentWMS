package org.iwms.server.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author leung
 */
@Component
public class MessageSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(String queueName, String message) {
        amqpTemplate.convertAndSend(queueName, message);
        System.out.println("Message sent to queue: " + queueName);
    }
}
