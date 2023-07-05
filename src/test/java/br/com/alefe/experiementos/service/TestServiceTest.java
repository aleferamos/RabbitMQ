package br.com.alefe.experiementos.service;

import br.com.alefe.experiementos.model.Message;
import br.com.alefe.experiementos.model.enums.MessageType;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.*;

@MockitoSettings
class TestServiceTest {

    @InjectMocks
    TestService testService;

    @Test
    void test(){
        testService.executar(Message.builder()
                        .text("Hello world")
                        .type(MessageType.WHATSAPP)
                .build());
    }
}