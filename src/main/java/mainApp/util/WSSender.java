package mainApp.util;

import mainApp.dto.EventType;
import mainApp.dto.ObjectType;
import mainApp.dto.WSEventDto;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.BiConsumer;

//@Component
public class WSSender {
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private final ObjectMapper objectMapper;

    public WSSender(SimpMessagingTemplate template, ObjectMapper objectMapper){
        this.simpMessagingTemplate = template;
        this.objectMapper = objectMapper;
    }

    public <T> BiConsumer<EventType, T> getSender(ObjectType type, Class view){
        ObjectWriter object = objectMapper
                .setSerializationConfig(objectMapper.getSerializationConfig())
                .writerWithView(view);
        return (EventType event, T payload) -> {
            String value = null;
            try {
                value = object.writeValueAsString(payload);
            }catch (IOException ex){
                throw new RuntimeException(ex);
            }
            simpMessagingTemplate.convertAndSend("/topic/activity", new WSEventDto(type, event, value));
        };
    }
}
