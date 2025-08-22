package io.stockmarket;

public sealed abstract class Order permits Order.Sell, Order.Purchase {

    private final Stock stock;
    private final int price;

    private Order(Stock stock, final int price) {
        this.stock = stock;
        this.price = price;
    }

    public static Order sell(int price, Stock stock) {
        return new Sell(price, stock);
    }

    public static Order purchase(int price, Stock stock) {
        return new Purchase(price, stock);
    }

    public abstract boolean isSell();

    public abstract boolean isPurchase();

    public int getPrice() {
        return price;
    }

    public Stock getStock() {
        return stock;
    }

    public void updateStockPrice() {
        stock.updatePrice(price);
    }

    public final static class Sell extends Order {

        private Sell(int price, Stock stock) {
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

        private Purchase(int price, Stock stock) {
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
