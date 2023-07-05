package br.com.alefe.experiementos.model;

import br.com.alefe.experiementos.model.enums.MessageType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {
    private String text;
    private MessageType type;
}
