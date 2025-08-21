package io.library;

public sealed interface DeliveryStrategy permits SedexDeliveryStrategy, PACDeliveryStrategy, PickupStrategy {

    Money calculateDelivery(final Order order);
}
