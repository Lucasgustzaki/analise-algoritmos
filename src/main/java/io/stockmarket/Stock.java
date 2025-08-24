package io.stockmarket;

import java.util.Objects;

public final class Stock {

    private final StockCode code;

    private Money price;

    private Stock(final StockCode code, final Money price) {
        this.code = code;
        this.price = price;
    }

    public static Stock of(final String code, final Money price) {
        return new Stock(new StockCode(code), price);
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

    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(code, stock.code);
    }

    public static final class StockCode {

        private final String name;

        public StockCode(final String name) {
            this.name = name.trim().toUpperCase();
        }

        @Override
        public boolean equals(final Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            StockCode stockCode = (StockCode) o;
            return Objects.equals(name, stockCode.name);
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
