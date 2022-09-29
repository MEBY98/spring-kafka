package com.example.meby98.initprojectkafka;

import com.example.meby98.initprojectkafka.Models.Order;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ConsumingMessage {

    private final OrderRepository orderRepository;

    @KafkaListener(topics = "topic_0", groupId = "orders", containerFactory = "ConcurrentKafkaListenerContainer")
    public void saveOrdersFromKafka(Order order) {
        this.orderRepository.save(order);
    }
}
