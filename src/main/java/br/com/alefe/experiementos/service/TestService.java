package br.com.alefe.experiementos.service;

import br.com.alefe.experiementos.model.Message;
import br.com.alefe.experiementos.model.interfaces.ProcessMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private ProcessMessage processMessage;

    public void executar(Message message){
        processMessage.execute(message);
    }
}
