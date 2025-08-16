package io.library;

import java.math.BigDecimal;

public final class SedexDeliveryStrategy implements DeliveryStrategy {

    @Override
    public Money calculateDelivery(final Order order) {
        var weight = order.getTotalWeight();
        
        if (weight <= 0.5) {
            return Money.of(12.50);
        }

        if (weight <= 1.0) {
            return Money.of(20.00);
        }

        return calculateAdditionalCost(weight);
    }

    private Money calculateAdditionalCost(final double weight) {
        double extraWeight = weight - 1.0;

        int extra100gBlocks = (int) Math.ceil(extraWeight * 10);

        BigDecimal baseCost = BigDecimal.valueOf(46.50);
        BigDecimal additionalCost = BigDecimal.valueOf(extra100gBlocks * 1.50);

        return new Money(baseCost.add(additionalCost));
    }
}
