package com.example.meby98.initprojectkafka.Configuration;

import java.util.HashMap;
import java.util.Map;

import com.example.meby98.initprojectkafka.Models.Order;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
public class KafkaConsumerConfiguration {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Bean
    public <T> ConsumerFactory<String, T> consumerFactory(String groupId, Class<T> valueClass) {
        Map<String, Object> config = new HashMap<>();

        // Server URL
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        config.put(ConsumerConfig.GROUP_ID_CONFIG,
                groupId);

        // Authentication conflount.io cluster
        config.put("sasl.mechanism", "PLAIN");
        config.put("sasl.jaas.config",
                "org.apache.kafka.common.security.plain.PlainLoginModule required username='{{ API KEY }}'   password='{{ API SECRET }}';");
        config.put("security.protocol", "SASL_SSL");

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(valueClass));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Order> ConcurrentKafkaListenerContainer() {
        ConcurrentKafkaListenerContainerFactory<String, Order> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory("orders", Order.class));
        return factory;
    }
}
