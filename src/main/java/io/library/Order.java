package io.library;

import java.util.List;

public final class Order {

    private final List<Product> products;

    public Order(final Product... products) {
        this.products = List.of(products);
    }

    public Weight getTotalWeight() {
        return products.stream()
                .map(Product::getWeight)
                .reduce(Weight.zero(), Weight::add);
    }

    public Money calculateDelivery(final DeliveryStrategy strategy) {
        return strategy.calculateDelivery(this);
    }
}
