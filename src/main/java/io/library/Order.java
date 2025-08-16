package io.library;

import java.util.List;

public final class Order {

    private final List<Product> products;

    public Order(final Product... products) {
        this.products = List.of(products);
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalWeight() {
        return products.stream()
                .mapToDouble(Product::getWeight)
                .sum();
    }
}
