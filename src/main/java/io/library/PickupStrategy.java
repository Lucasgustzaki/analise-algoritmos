package io.library;

public final class PickupStrategy implements DeliveryStrategy {

    @Override
    public Money calculateDelivery(final Order order) {
        return Money.zero();
    }
}
