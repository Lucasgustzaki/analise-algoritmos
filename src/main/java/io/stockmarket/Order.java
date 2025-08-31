package io.stockmarket;

public sealed abstract class Order permits Order.Sell, Order.Purchase {

    private final Investor investor;
    private final Money orderPrice;
    private final Stock stock;

    private Order(final Investor investor, final Stock stock, final Money orderPrice) {
        this.investor = investor;
        this.orderPrice = orderPrice;
        this.stock = stock;
    }

    public static Order sell(final Investor investor, final Money price, final Stock stock) {
        return new Sell(investor, price, stock);
    }

    public static Order purchase(final Investor investor, final Money price, final Stock stock) {
        return new Purchase(investor, price, stock);
    }

    public abstract boolean isSell();

    public abstract boolean isPurchase();

    public Money getPrice() {
        return orderPrice;
    }

    public Stock getStock() {
        return stock;
    }

    public Investor madeBy() {
        return investor;
    }

    public void updateStockPrice() {
        stock.updatePrice(orderPrice);
    }

    public final static class Sell extends Order {

        private Sell(final Investor investor, final Money price, final Stock stock) {
            super(investor, stock, price);
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

        private Purchase(final Investor investor, final Money price, final Stock stock) {
            super(investor, stock, price);
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
