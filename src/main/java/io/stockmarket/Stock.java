package io.stockmarket;

public final class Stock {

    private final StockCode code;

    private Money price;

    private Stock(final StockCode code, final Money price) {
        this.code = code;
        this.price = price;
    }

    public static Stock of(final Money price) {
        return new Stock(StockCode.DEFAULT, price);
    }

    void updatePrice(final Money newPrice) {
        this.price = newPrice;
    }

    public Money getPrice() {
        return price;
    }

    public StockCode getCode() {
        return code;
    }

    private static final class StockCode {

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
