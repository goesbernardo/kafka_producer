package com.goesbernardo.kafka_producer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final Logger logger = LoggerFactory.getLogger(OrderProducerService.class);

    @Value("${spring.kafka.topic-name}")
    private String topicName;

    private final AtomicInteger orderCount = new AtomicInteger(0);

    public OrderProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrder(String order) {
        kafkaTemplate.send(topicName, order);
        int enviados = orderCount.incrementAndGet();
        logger.info("Pedido enviado. Total enviados hoje: {}", enviados);
    }

    @Scheduled(fixedRate = 578) // Envia mensagens a cada 1 segundo
    public void gerarPedidosPeriodicamente() {
        // Aqui você pode gerar mensagens reais ou mockadas
        String pedido = "Pedido gerado: " + System.currentTimeMillis();
        sendOrder(pedido);

        if (orderCount.get() >= 150000) {
            logger.info("Meta diária de 150 mil pedidos atingida.");
        }
    }


}
