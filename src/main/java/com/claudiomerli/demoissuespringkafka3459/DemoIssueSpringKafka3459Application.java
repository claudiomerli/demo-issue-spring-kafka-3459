package com.claudiomerli.demoissuespringkafka3459;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class DemoIssueSpringKafka3459Application{

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DemoIssueSpringKafka3459Application.class, args);
    }

    @Scheduled(fixedRate = 1000)
    public void scheduledSend() throws Exception {
        this.kafkaTemplate.send("test","TEST MESSAGE");
    }
}
