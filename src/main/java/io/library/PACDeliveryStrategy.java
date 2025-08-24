package io.library;

public final class PACDeliveryStrategy implements DeliveryStrategy {

    private static final DeliveryStrategy INSTANCE = new PACDeliveryStrategy();

    private PACDeliveryStrategy() {
        // Private constructor to enforce singleton pattern
    }

    public static DeliveryStrategy getInstance() {
        return INSTANCE;
    }

    @Override
    public Money calculateDelivery(final Order order) {
        var weight = order.getTotalWeight();

        if (weight.lessThanOrEqualTo(Weight.grams(1000))) {
            return Money.of(10.00);
        } 
        
        if (weight.lessThanOrEqualTo(Weight.grams(2000))) {
            return Money.of(15.00);
        } 

        throw new OrderNotAcceptedException("Order not accepted due to total weight above 2 pounds");
    }
}
