package com.example.delivery2consumer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public class DelivererCoordinatesMessage {
    private UUID orderId;
    private double delivererLatitude;
    private double delivererLongitude;

    private double customerLatitude;

    private double customerLongitude;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    public DelivererCoordinatesMessage() {
    }

    public DelivererCoordinatesMessage(UUID orderId, double delivererLatitude, double delivererLongitude, double customerLatitude, double customerLongitude, LocalDateTime timestamp) {
        this.orderId = orderId;
        this.delivererLatitude = delivererLatitude;
        this.delivererLongitude = delivererLongitude;
        this.customerLatitude = customerLatitude;
        this.customerLongitude = customerLongitude;
        this.timestamp = timestamp;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public double getDelivererLatitude() {
        return delivererLatitude;
    }

    public void setDelivererLatitude(double delivererLatitude) {
        this.delivererLatitude = delivererLatitude;
    }

    public double getDelivererLongitude() {
        return delivererLongitude;
    }

    public void setDelivererLongitude(double delivererLongitude) {
        this.delivererLongitude = delivererLongitude;
    }

    public double getCustomerLatitude() {
        return customerLatitude;
    }

    public void setCustomerLatitude(double customerLatitude) {
        this.customerLatitude = customerLatitude;
    }

    public double getCustomerLongitude() {
        return customerLongitude;
    }

    public void setCustomerLongitude(double customerLongitude) {
        this.customerLongitude = customerLongitude;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
