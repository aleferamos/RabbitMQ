package br.com.alefe.experiementos.model.interfaces.impl;

import br.com.alefe.experiementos.model.Message;
import br.com.alefe.experiementos.model.interfaces.ProcessMessage;
import org.springframework.stereotype.Component;

@Component
public class ProcessMessageWhatsapp implements ProcessMessage {
    @Override
    public void execute(Message message) {
        System.out.println("Mensagem processada| Tipo: Sms");
    }
}
