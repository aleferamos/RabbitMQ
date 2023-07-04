package br.com.alefe.experiementos;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ConsumerSendEmail {

    @RabbitListener(queues = {"${queue.name}"})
    public void receive(@Payload Person person){
        System.out.println(person.toString());
    }
}
