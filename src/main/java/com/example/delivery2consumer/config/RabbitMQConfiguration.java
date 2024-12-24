package com.example.delivery2consumer.config;


import com.example.delivery2consumer.dto.OrderStatusUpdateMessage;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class RabbitMQConfiguration {

    static final String exchangeName = "grpcExchange";

    @Bean
    public Queue discountQueue() {

        return new Queue("discountQueue", false);
    }
    @Bean
    public Queue coordinatesQueue() {
        return new Queue("coordinatesQueue", false);
    }
    @Bean
    public Queue deliveryQueue() {
        return new Queue("deliveryQueue", false);
    }
    @Bean
    public Queue noticeQueue() {
        return new Queue("noticeQueue", false);
    }
    @Bean
    public Queue auditDeliveryQueue() {
        return new Queue("auditDeliveryQueue", false);
    }
    @Bean
    public Queue auditNoticeQueue() {
        return new Queue("auditNoticeQueue", false);
    }
    @Bean
    public Map<UUID, OrderStatusUpdateMessage> orderStatusMap() {
        return new HashMap<>();
    }
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName, false, false);
    }


    @Bean
    public Binding notifierBinding(Queue noticeQueue, TopicExchange exchange) {
        return BindingBuilder.bind(noticeQueue).to(exchange).with("my.key");
    }
    @Bean
    public Binding deliveryBinding(Queue deliveryQueue, TopicExchange exchange) {
        return BindingBuilder.bind(deliveryQueue).to(exchange).with("delivery.result");
    }
    @Bean
    public Binding auditNotifierBinding(Queue auditNoticeQueue, TopicExchange exchange) {
        return BindingBuilder.bind(auditNoticeQueue).to(exchange).with("my.key");
    }
    @Bean
    public Binding auditDeliveryBinding(Queue auditDeliveryQueue, TopicExchange exchange) {
        return BindingBuilder.bind(auditDeliveryQueue).to(exchange).with("delivery.result");
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}

