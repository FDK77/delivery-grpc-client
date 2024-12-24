package com.example.delivery2consumer.services;

import com.example.delivery2consumer.models.enums.Status;
import com.example.delivery2consumer.dto.OrderStatusUpdateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
public class DiscountServiceCheck {


    private GrpcService discountService;
    @Autowired
    public void setDiscountService(GrpcService discountService) {
        this.discountService = discountService;
    }

    public void checkDiscount(Map<UUID, OrderStatusUpdateMessage> orderStatusMap, UUID orderId, String newStatus, LocalDateTime messageTime) {
        if (orderStatusMap.containsKey(orderId)) {
            OrderStatusUpdateMessage existingMessage = orderStatusMap.get(orderId);
            Duration duration = Duration.between(existingMessage.getDate(), messageTime);

            if (existingMessage.getStatus().equals(Status.IN_TRANSIT.name()) && duration.toSeconds() > 1) {
                System.out.println("Заказ ID: " + orderId + " был в статусе IN_TRANSIT более 20 минут.");
                discountService.applyDiscount(orderId, 0.2);
            } else if (existingMessage.getStatus().equals(Status.ASSEMBLING.name()) && duration.toSeconds() > 1) {
                System.out.println("Заказ ID: " + orderId + " был в статусе ASSEMBLING более 10 минут.");
                discountService.applyDiscount(orderId, 0.1);
            } else if (existingMessage.getStatus().equals(Status.ARRIVED.name()) && duration.toSeconds() > 1) {
                System.out.println("Заказ ID: " + orderId + " был в статусе ARRIVED более 5 минут.");
                discountService.applyDiscount(orderId, 0.05);
            }
            existingMessage.setStatus(newStatus);
            existingMessage.setDate(messageTime);
        } else {
            orderStatusMap.put(orderId, new OrderStatusUpdateMessage(orderId, newStatus, messageTime));
            System.out.println("Сообщение из очереди: Заказ ID: " + orderId + ", Текущий статус: " + newStatus + " Время: " + messageTime);
        }
    }


}
