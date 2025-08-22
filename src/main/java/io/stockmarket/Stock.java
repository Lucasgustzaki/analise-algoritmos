package io.stockmarket;

public class Stock {

    private final StockCode code;
    private int price;

    private Stock(final StockCode code, final int price) {
        this.code = code;
        this.price = price;
    }

    public static Stock of(int price) {
        return new Stock(StockCode.DEFAULT, price);
    }

    protected void updatePrice(int newPrice) {
        this.price = newPrice;
    }

    public int getPrice() {
        return price;
    }

    public StockCode getCode() {
        return code;
    }

    public static final class StockCode {

        private static final StockCode DEFAULT = new StockCode("DEFAULT");

        private final String name;

        public StockCode(final String name) {
            this.name = name.trim().toUpperCase();
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
