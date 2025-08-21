package io.stockmarket;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class StockTest {

    @Test
    void whenOrderIsPushedDoNothing() {
        Stock stock = createStock();

        stock.pushOrder(new Order());
    }

    @Test
    void whenMultipleOrdersArePushedDoNothing() {
        Stock stock = createStock();

        pushMultipleOrders(stock, anyCount());
    }

    @Test
    void whenInvestorIsRegisteredThenDoNothing() {
        Stock stock = createStock();

        stock.registerInvestor(John());
    }

    @Test
    void whenInvestorIsRegisteredThenIsNotNotified() {
        Investor investor = John();

        Stock stock = createStock();

        stock.registerInvestor(investor);

        assertThatWasNotNotified(investor);
    }

    @Test
    void whenInvestorIsRegisteredAndNoMatchOnStockMarketThenInvestorIsNotNotified() {
        Investor investor = John();
        Stock stock = createStock();

        stock.registerInvestor(investor);

        pushMultipleOrders(stock, anyCount());

        assertThatWasNotNotified(investor);
    }

    @Test
    void whenInvestorIsRegisteredAndMatchOnStockMarketThenInvestorIsNotified() {
        Investor investor = John();
        Stock stock = createStock();

        stock.registerInvestor(investor);

        stock.pushOrder(purchase(24));
        stock.pushOrder(sell(24));

        assertThatWasNotified(investor);
    }

    private void pushMultipleOrders(Stock stock, int count) {
        for (int i = 0; i < count; i++) {
            stock.pushOrder(new Order());
        }
    }

    private int anyCount() {
        return 10;
    }

    private Investor John() {
        return new Investor();
    }

    private Stock createStock() {
        return new Stock();
    }

    private void assertThatWasNotNotified(Investor investor) {
        assertFalse(investor.wasNotified(), "Investor should not have been notified");
    }

    private void assertThatWasNotified(Investor investor) {
        assertFalse(investor.wasNotified(), "Investor should not have been notified");
    }

    private Order sell(int price) {
        return new Order();
    }

    private Order purchase(int price) {
        return new Order();
    }
}
