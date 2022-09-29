package com.example.meby98.initprojectkafka.Configuration;

import java.util.HashMap;
import java.util.Map;

import com.example.meby98.initprojectkafka.Models.Order;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaProducerConfiguration {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Bean
    public ProducerFactory<String, Order> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        // Server URL
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);

        // Authentication conflount.io cluster
        configProps.put("sasl.mechanism", "PLAIN");
        configProps.put("sasl.jaas.config",
                "org.apache.kafka.common.security.plain.PlainLoginModule required username='6LHUFL56AE4OF7M2'   password='rXbeeL9U2AKnFSqb+Bbb61kLOl58gojMDmpLjbPbaPJBvHNYOppECuj8flEwrVJe';");
        configProps.put("security.protocol", "SASL_SSL");

        // Serializers Strings
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, Order> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
