package io.library;

public final class DeliveryCalculator {

    private DeliveryStrategy deliveryStrategy;

    public DeliveryCalculator(final DeliveryStrategy deliveryStrategy) {
        this.deliveryStrategy = deliveryStrategy;
    }

    public void setDeliveryStrategy(final DeliveryStrategy deliveryStrategy) {
        this.deliveryStrategy = deliveryStrategy;
    }

    public Money calculateDelivery(final Order order) {
        return deliveryStrategy.calculateDelivery(order);
    }
}
