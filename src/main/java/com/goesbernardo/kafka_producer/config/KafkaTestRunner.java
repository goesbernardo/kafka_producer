package com.goesbernardo.kafka_producer.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaTestRunner implements CommandLineRunner {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaTestRunner(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        kafkaTemplate.send("pedidos-diarios", "Teste de conexão com Kafka");
        System.out.println("Mensagem de teste enviada para o Kafka!");
    }


}
