package com.example.delivery2consumer.dto;

import java.util.UUID;

public class DeliveryResult {
    private UUID orderId;
    private double distance;
    private int estimatedTimeMinutes;
    private int estimatedTimeSeconds;

    public DeliveryResult(UUID orderId, double distance, int estimatedTimeMinutes, int estimatedTimeSeconds) {
        this.orderId = orderId;
        this.distance = distance;
        this.estimatedTimeMinutes = estimatedTimeMinutes;
        this.estimatedTimeSeconds = estimatedTimeSeconds;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public double getDistance() {
        return distance;
    }

    public int getEstimatedTimeMinutes() {
        return estimatedTimeMinutes;
    }

    public int getEstimatedTimeSeconds() {
        return estimatedTimeSeconds;
    }


    @Override
    public String toString() {
        return "Заказ: " + orderId +
                ", Расстояние: " + distance + " км" +
                ", Время доставки: " + estimatedTimeMinutes + " минут и " + estimatedTimeSeconds + " секунд";
    }
}
