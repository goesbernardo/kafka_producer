package com.goesbernardo.kafka_producer.controller;

import com.goesbernardo.kafka_producer.service.OrderProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderProducerController {

    @Autowired
    private final OrderProducerService orderProducerService;

    public OrderProducerController(OrderProducerService orderProducerService) {
        this.orderProducerService = orderProducerService;
    }

    @GetMapping("/send-order")
    public String enviarPedido(@RequestParam String order) {
        orderProducerService.sendOrder(order);
        return "Pedido enviado: " + order;
    }
}
