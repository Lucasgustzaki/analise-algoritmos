package io.library;

import java.math.BigDecimal;

public final class PACDeliveryStrategy implements DeliveryStrategy {

    @Override
    public Money calculateDelivery(final Order order) {
        var weight = order.getTotalWeight();

        if (weight <= 1.0) {
            return Money.of(10.00);
        } 
        
        if (weight <= 2.0) {
            return Money.of(15.00);
        } 

        throw new OrderNotAcceptedException("Order not accepted due to total weight above 2 pounds");
    }
}
