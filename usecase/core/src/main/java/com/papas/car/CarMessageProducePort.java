package com.papas.car;

public interface CarMessageProducePort {
    void sendCarMessage(SaveCarEvent event);
}
