package br.com.alefe.experiementos.model.interfaces.impl;

import br.com.alefe.experiementos.model.Message;
import br.com.alefe.experiementos.model.interfaces.ProcessMessage;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class ProcessMessageSms implements ProcessMessage {
    @Override
    public void execute(Message message) {
        System.out.println("Mensagem processada| Tipo: Sms");
    }
}
