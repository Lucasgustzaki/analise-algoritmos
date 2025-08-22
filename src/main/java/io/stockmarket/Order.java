package io.stockmarket;

public class Order {

    private final int price;
    private final Type type;

    private Order(final int price, final Type type) {
        this.price = price;
        this.type = type;
    }

    public static Order sell(int price) {
        return new Order(price, Type.SELL);
    }

    public static Order purchase(int price) {
        return new Order(price, Type.PURCHASE);
    }

    public Type type() {
        return type;
    }

    public boolean isSell() {
        return type == Type.SELL;
    }

    public boolean isPurchase() {
        return type == Type.PURCHASE;
    }

    public int price() {
        return price;
    }

    private enum Type {
        SELL, PURCHASE
    }
}
