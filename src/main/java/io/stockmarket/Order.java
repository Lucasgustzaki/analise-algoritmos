package io.stockmarket;

public class Order {

    private final Type type;

    private Order(Type type) {
        this.type = type;
    }

    public static Order sell() {
        return new Order(Type.SELL);
    }

    public static Order purchase() {
        return new Order(Type.PURCHASE);
    }

    public Type type() {
        return type;
    }

    public boolean isSell() {
        return false;
    }

    private enum Type {
        SELL, PURCHASE
    }
}
