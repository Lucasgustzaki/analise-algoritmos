package io.library;

public final class Product {

    private final String name;
    private final Money price;
    private final Weight weight;

    public Product(final String name, final Money price, final Weight weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public Weight getWeight() {
        return weight;
    }
}
