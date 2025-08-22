package io.stockmarket;

public sealed abstract class Order permits Order.Sell, Order.Purchase {

    private final Money orderPrice;
    private final Stock stock;

    private Order(final Stock stock, final Money orderPrice) {
        this.orderPrice = orderPrice;
        this.stock = stock;
    }

    public static Order sell(Money price, Stock stock) {
        return new Sell(price, stock);
    }

    public static Order purchase(Money price, Stock stock) {
        return new Purchase(price, stock);
    }

    public abstract boolean isSell();

    public abstract boolean isPurchase();

    public Money getPrice() {
        return orderPrice;
    }

    public Stock getStock() {
        return stock;
    }

    public void updateStockPrice() {
        stock.updatePrice(orderPrice);
    }

    public final static class Sell extends Order {

        private Sell(Money price, Stock stock) {
            super(stock, price);
        }

        @Override
        public boolean isSell() {
            return true;
        }

        @Override
        public boolean isPurchase() {
            return false;
        }
    }

    public final static class Purchase extends Order {

        private Purchase(Money price, Stock stock) {
            super(stock, price);
        }

        @Override
        public boolean isSell() {
            return false;
        }

        @Override
        public boolean isPurchase() {
            return true;
        }
    }
}
