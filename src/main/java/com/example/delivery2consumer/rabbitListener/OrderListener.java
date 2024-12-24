package com.example.delivery2consumer.rabbitListener;

import com.example.delivery2consumer.dto.DelivererCoordinatesMessage;
import com.example.delivery2consumer.dto.DeliveryResult;
import com.example.delivery2consumer.dto.OrderStatusUpdateMessage;
import com.example.delivery2consumer.services.DiscountServiceCheck;
import com.example.delivery2consumer.services.GrpcService; // Импорт GrpcService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Component
public class OrderListener {

    private final DiscountServiceCheck discountService;
    private final Map<UUID, OrderStatusUpdateMessage> orderStatusMap;
    private final GrpcService grpcService;

    @Autowired
    public OrderListener(DiscountServiceCheck discountService, Map<UUID, OrderStatusUpdateMessage> orderStatusMap, GrpcService grpcService) {
        this.discountService = discountService;
        this.orderStatusMap = orderStatusMap;
        this.grpcService = grpcService;
    }

    @RabbitListener(queues = "discountQueue")
    public void handleStatusUpdate(OrderStatusUpdateMessage message) {
        UUID orderId = message.getOrderId();
        String newStatus = message.getStatus();
        LocalDateTime messageTime = message.getDate();
        System.out.println(message);
        discountService.checkDiscount(orderStatusMap, orderId, newStatus, messageTime);
    }

    @RabbitListener(queues = "coordinatesQueue")
    public void handleDelivererCoordinates(DelivererCoordinatesMessage message) {
        UUID orderId = message.getOrderId();
        double delivererLatitude = message.getDelivererLatitude();
        double delivererLongitude = message.getDelivererLongitude();
        double customerLatitude = message.getCustomerLatitude();
        double customerLongitude = message.getCustomerLongitude();
        System.out.println("Координаты доставщика изменились для заказа ID: " + orderId);
        DeliveryResult result = grpcService.calculateDelivery(orderId, delivererLatitude, delivererLongitude, customerLatitude, customerLongitude);
        if (result != null) {
            System.out.println(result);
        } else {
            System.out.println("Ошибка расчета доставки для заказа ID: " + orderId);
        }
    }

}
