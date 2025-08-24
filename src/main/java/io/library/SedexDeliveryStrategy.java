package io.library;

public final class SedexDeliveryStrategy implements DeliveryStrategy {

    private static final DeliveryStrategy INSTANCE = new SedexDeliveryStrategy();

    private SedexDeliveryStrategy() {
        // Singleton
    }

    public static DeliveryStrategy getInstance() {
        return INSTANCE;
    }

    @Override
    public Money calculateDelivery(final Order order) {
        Weight weight = order.getTotalWeight();
        
        if (weight.lessThanOrEqualTo(Weight.grams(500))) {
            return Money.of(12.50);
        }

        if (weight.lessThanOrEqualTo(Weight.grams(1000))) {
            return Money.of(20.00);
        }

        return calculateAdditionalCost(weight);
    }

    private Money calculateAdditionalCost(final Weight weight) {
        Weight extraWeight = weight.sub(Weight.grams(1000));

        int extra100gBlocks = extraWeight.countBlocks(Weight.grams(100));

        Money baseCost = Money.of(46.50);
        Money additionalCost = Money.of(extra100gBlocks * 1.50);

        return baseCost.add(additionalCost);
    }
}
