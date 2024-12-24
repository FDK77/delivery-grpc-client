package com.example.delivery2consumer.services;

import com.example.delivery2consumer.dto.DeliveryResult;
import com.example.delivery2consumer.models.Order;
import com.example.delivery2consumer.repository.OrderRepository;
import com.example.discount.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class GrpcService {

    private final ManagedChannel backendChannel;
    private final DiscountBackendServiceGrpc.DiscountBackendServiceBlockingStub backendStub;
    private final OrderRepository orderRepository;
    private final RabbitSendService rabbitSendService;

    @Autowired
    public GrpcService(OrderRepository orderRepository, RabbitSendService rabbitSendService) {
        this.rabbitSendService = rabbitSendService;
        this.backendChannel = ManagedChannelBuilder.forAddress("localhost", 9091)
                .usePlaintext()
                .build();
        this.backendStub = DiscountBackendServiceGrpc.newBlockingStub(backendChannel);
        this.orderRepository = orderRepository;
    }

    public void applyDiscount(UUID orderId, double discount) {
        EligibilityRequest eligibilityRequest = EligibilityRequest.newBuilder()
                .setOrderId(orderId.toString())
                .build();
        try {
            EligibilityResponse eligibilityResponse = backendStub.checkDiscountEligibility(eligibilityRequest);
            if (!eligibilityResponse.getIsEligible()) {
                System.out.println("Скидка не может быть применена к заказу ID: " + orderId);
                return;
            }

            Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
            double oldPrice = order.getOrderCost();
            double newPrice = oldPrice - (oldPrice * discount);
            order.setOrderCost(newPrice);
            orderRepository.save(order);
            System.out.println("Скидка успешно применена к заказу ID: " + orderId);
            System.out.println("Старая цена: " + oldPrice + ", Новая цена: " + newPrice);
            rabbitSendService.sendDiscountMessage(orderId, LocalDateTime.now(),oldPrice,newPrice,discount);
        } catch (StatusRuntimeException e){
            System.err.println("Ошибка вызова Grpc");
        }
    }

    public DeliveryResult calculateDelivery(UUID orderId, double delivererLatitude, double delivererLongitude, double customerLatitude, double customerLongitude) {
        DeliveryRequest request = DeliveryRequest.newBuilder()
                .setOrderId(orderId.toString())
                .setDelivererLatitude(delivererLatitude)
                .setDelivererLongitude(delivererLongitude)
                .setCustomerLatitude(customerLatitude)
                .setCustomerLongitude(customerLongitude)
                .build();
        try {
            DeliveryCalculationServiceGrpc.DeliveryCalculationServiceBlockingStub deliveryStub =
                    DeliveryCalculationServiceGrpc.newBlockingStub(backendChannel);

            DeliveryResponse response = deliveryStub.calculateDelivery(request);
            double distance = response.getDistance();
            int totalSeconds = response.getEstimatedTimeMinutes() * 60 + response.getEstimatedTimeSeconds();
            if (totalSeconds < 30) {
                distance = 0.0;
            }
            DeliveryResult deliveryResult = new DeliveryResult(
                    UUID.fromString(response.getOrderId()),
                    distance,
                    response.getEstimatedTimeMinutes(),
                    response.getEstimatedTimeSeconds()
            );
            rabbitSendService.sendDeliveryResult(deliveryResult);
            return deliveryResult;
        } catch (StatusRuntimeException e) {
            System.err.println("Ошибка вызова gRPC: " + e.getMessage());
            return null;
        }
    }



}
