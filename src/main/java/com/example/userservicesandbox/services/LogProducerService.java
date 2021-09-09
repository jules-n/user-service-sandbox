package com.example.userservicesandbox.services;

import com.example.userservicesandbox.domain.LogDTO;
import com.example.userservicesandbox.util.convertors.KafkaMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LogProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaMessageConverter kafkaMessageConverter;

    public void produce(LogDTO log) {
        var message = kafkaMessageConverter.serialize(log);
        kafkaTemplate.send("logs", message);
    }
}