package com.example.delivery2consumer.services;

import com.example.delivery2consumer.dto.DeliveryResult;
import com.example.delivery2consumer.dto.OrderDiscountMessage;
import com.example.delivery2consumer.dto.OrderStatusUpdateMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class RabbitSendService {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    static final String exchangeName = "grpcExchange";


    @Autowired
    public RabbitSendService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendDiscountMessage(UUID orderId, LocalDateTime time, Double oldPrice, Double newPrice, Double discount ) {
        OrderDiscountMessage message = new OrderDiscountMessage(orderId,time,oldPrice,newPrice,discount);
        System.out.println("СООБЩЕНИЕ: " + message.toString());
        rabbitTemplate.convertAndSend(exchangeName, "my.key", message);
    }

    public void sendDeliveryResult(DeliveryResult deliveryResult) {
        rabbitTemplate.convertAndSend(exchangeName, "delivery.result", deliveryResult);
        System.out.println("DeliveryResult отправлено: " + deliveryResult);
    }



}

