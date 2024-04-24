package org.iwms.server.test;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Queue;

@SpringBootApplication
@EnableRabbit
public class RabbitmqDemoApplication {

    public static final String QUEUE_NAME = "my-queue";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqDemoApplication.class, args);
    }

    @Bean
    public Queue myQueue() {
        return new Queue(QUEUE_NAME, true); // 创建一个持久化的队列
    }

    @RabbitListener(queues = QUEUE_NAME)
    public void listen(String in) {
        System.out.println("Received: " + in);
    }

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(QUEUE_NAME, message);
        System.out.println("Sent: " + message);
    }
}
