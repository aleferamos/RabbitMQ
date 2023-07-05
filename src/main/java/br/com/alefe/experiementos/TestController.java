package br.com.alefe.experiementos;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    private final AmqpTemplate queueSender;

    public TestController(AmqpTemplate queueSender) {
        System.out.println("entrou");
        this.queueSender = queueSender;
    }

    @GetMapping
    public String send() {
        queueSender.convertAndSend("send-email", "routing-key-send-email",
                new Person("alefe", 25));

        return "ok. done";
    }
}
