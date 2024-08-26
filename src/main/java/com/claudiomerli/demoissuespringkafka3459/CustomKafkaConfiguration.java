package com.claudiomerli.demoissuespringkafka3459;

import jakarta.annotation.PostConstruct;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;

@Configuration
public class CustomKafkaConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "demo.concurrency", value = "enabled")
    ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactoryWithConcurrency(
            ConsumerFactory<String, String> kafkaConsumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConcurrency(10);
        factory.getContainerProperties().setObservationEnabled(true);
        factory.setConsumerFactory(kafkaConsumerFactory);
        return factory;
    }

    @Bean
    @ConditionalOnMissingBean({ConcurrentKafkaListenerContainerFactory.class})
    ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
            ConsumerFactory<String, String> kafkaConsumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.getContainerProperties().setObservationEnabled(true);
        factory.setConsumerFactory(kafkaConsumerFactory);
        return factory;
    }

    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name("test")
                .partitions(10)
                .build();
    }

}
