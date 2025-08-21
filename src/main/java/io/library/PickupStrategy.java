package io.library;

public final class PickupStrategy implements DeliveryStrategy {

    private static final DeliveryStrategy INSTANCE = new PickupStrategy();

    private PickupStrategy() {
        // Singleton
    }

    public static DeliveryStrategy getInstance() {
        return INSTANCE;
    }

    @Override
    public Money calculateDelivery(final Order order) {
        return Money.zero();
    }
}
