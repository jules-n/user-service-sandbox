package com.example.userservicesandbox.util.convertors;

import com.example.userservicesandbox.domain.Log;
import com.example.userservicesandbox.domain.LogDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogKafkaMessageConverterJSONImpl implements KafkaMessageConverter<Log, LogDTO> {

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public <Log> Log deserialize(String msg, Class<Log> dtoType) {
        return objectMapper.readValue(msg, dtoType);
    }

    @SneakyThrows
    @Override
    public String serialize(LogDTO object) {
        return objectMapper.writeValueAsString(object);
    }
}
