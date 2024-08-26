package com.claudiomerli.demoissuespringkafka3459;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

@Configuration
@Slf4j
public class Listeners {

    @KafkaListener(topics = "test")
    private void listener(@Payload String payload) {
        log.info("Payload {}", payload);
    }

}
