package io.stockmarket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {

    private StockMarket stockMarket;

    @BeforeEach
    void setUp() {
        stockMarket = new StockMarket();
    }

    @Test
    void whenOrderIsPushedDoNothing() {
        Stock stock = Stock.of(anyPrice());

        stockMarket.pushOrder(Order.purchase(24, stock));
    }

    @Test
    void whenMultipleOrdersArePushedDoNothing() {
        pushMultipleOrdersForStock(Stock.of(anyPrice()), anyCount());
    }

    @Test
    void whenInvestorIsRegisteredThenDoNothing() {
        stockMarket.registerInvestor(John());
    }

    @Test
    void whenInvestorIsRegisteredThenIsNotNotified() {
        Investor investor = John();

        stockMarket.registerInvestor(investor);

        assertThatWasNotNotified(investor);
    }

    @Test
    void whenMultipleInvestorsAreRegisteredThenNoIsNotified() {
        Investor john = John();
        Investor carl = Carl();

        stockMarket.registerInvestor(john);
        stockMarket.registerInvestor(carl);

        assertThatWasNotNotified(john);
        assertThatWasNotNotified(carl);
    }

    @Test
    void whenInvestorIsRegisteredAndNoMatchOnStockMarketThenInvestorIsNotNotified() {
        Investor investor = John();

        stockMarket.registerInvestor(investor);

        pushMultipleOrdersForStock(Stock.of(anyPrice()), anyCount());

        assertThatWasNotNotified(investor);
    }

    @Test
    void whenInvestorIsRegisteredAndMatchOnStockMarketThenInvestorIsNotified() {
        Investor investor = John();

        stockMarket.registerInvestor(investor);

        Stock stock = Stock.of(anyPrice());

        stockMarket.pushOrder(Order.purchase(24, stock));
        stockMarket.pushOrder(Order.sell(24, stock));

        assertThatWasNotified(investor);
    }

    @Test
    void whenInvestorIsRegisterButNoMatchOnStocksPriceThenInvestorIsNotNotified() {
        Investor investor = John();
        Stock stock = Stock.of(anyPrice());

        stockMarket.registerInvestor(investor);

        stockMarket.pushOrder(Order.purchase(24, stock));
        stockMarket.pushOrder(Order.sell(32, stock));

        assertThatWasNotNotified(investor);
    }

    @Test
    void whenMatchOccursLaterOnThenInvestorIsNotifiedAsWell() {
        Investor investor = John();

        Stock stock = Stock.of(anyPrice());

        stockMarket.registerInvestor(investor);

        stockMarket.pushOrder(Order.purchase(24, stock));
        stockMarket.pushOrder(Order.sell(32, stock));

        assertThatWasNotNotified(investor);

        stockMarket.pushOrder(Order.sell(24, stock));

        assertThatWasNotified(investor);
    }

    @Test
    void investorShouldNotBeNotifiedTwiceIfRegisteredTwice() {
        Investor investor = John();

        Stock stock = Stock.of(anyPrice());

        stockMarket.registerInvestor(investor);
        stockMarket.registerInvestor(investor);

        stockMarket.pushOrder(Order.purchase(24, stock));
        stockMarket.pushOrder(Order.sell(24, stock));

        assertThatWasNotified(investor);
        assertThatWasNotNotified(investor);
    }

    @Test
    void whenMatchOccursTwiceForSameOrderThenInvestorIsNotNotified() {
        Investor investor = John();

        Stock stock = Stock.of(anyPrice());

        stockMarket.registerInvestor(investor);

        stockMarket.pushOrder(Order.sell(24, stock));
        stockMarket.pushOrder(Order.purchase(24, stock));

        assertThatWasNotified(investor);

        stockMarket.pushOrder(Order.sell(24, stock));

        assertThatWasNotNotified(investor);
    }

    @Test
    void whenMatchOccursThenStockPriceIsChanged() {
        Investor investor = John();

        Stock stock = Stock.of(anyPrice());

        stockMarket.registerInvestor(investor);

        stockMarket.pushOrder(Order.purchase(24, stock));
        stockMarket.pushOrder(Order.sell(24, stock));

        assertThatWasNotified(investor);

        assertEquals(24, stock.getPrice());
    }

    @Test
    void whenMatchOccursThenStockPriceIsTheLastMatch() {
        Investor investor = John();

        Stock stock = Stock.of(anyPrice());

        stockMarket.registerInvestor(investor);

        stockMarket.pushOrder(Order.purchase(24, stock));
        stockMarket.pushOrder(Order.sell(24, stock));

        stockMarket.pushOrder(Order.purchase(32, stock));
        stockMarket.pushOrder(Order.sell(32, stock));

        assertThatWasNotified(investor);

        assertEquals(32, stock.getPrice());
    }

    private void pushMultipleOrdersForStock(final Stock stock, final int count) {
        for (int i = 0; i < count; i++) {
            stockMarket.pushOrder(Order.sell(anyPrice(), stock));
        }
    }

    private Investor John() {
        return new Investor();
    }

    private Investor Carl() {
        return new Investor();
    }

    private int anyPrice() {
        return 0;
    }

    private int anyCount() {
        return 10;
    }

    private void assertThatWasNotNotified(final Investor investor) {
        assertFalse(investor.wasNotified(), "Investor should not have been notified");
    }

    private void assertThatWasNotified(final Investor investor) {
        assertTrue(investor.wasNotified(), "Investor should not have been notified");
    }
}
