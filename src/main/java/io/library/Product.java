package io.library;

public final class Product {

    private final String name;
    private final Money price;
    private final float weight;

    public Product(final String name, final Money price, final float weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }
}
