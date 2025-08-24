package io.stockmarket;

public final class Notification {

    private static final String PRINT_MESSAGE = "[UPDATE] Stock market notification for stock: %s with price: %s";

    public Notification(final Stock stock) {
        System.out.printf((PRINT_MESSAGE) + "%n", stock.getCode(), stock.getPrice());
    }
}
