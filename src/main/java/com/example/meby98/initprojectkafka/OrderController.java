package com.example.meby98.initprojectkafka;

import java.util.List;

import javax.validation.Valid;

import com.example.meby98.initprojectkafka.DTO.OrderDTO;
import com.example.meby98.initprojectkafka.Models.Order;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, Order> kafkaTemplate;

    @GetMapping("api/orders")
    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    @PostMapping("api/orders")
    public void createOrder(@Valid @RequestBody OrderDTO newOrder) {
        Order order = new Order(newOrder);
        this.kafkaTemplate.send("topic_0", order);
    }
}
