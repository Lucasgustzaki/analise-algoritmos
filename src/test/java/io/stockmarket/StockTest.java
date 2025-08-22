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

        stockMarket.pushOrder(Order.purchase(anyPrice(), stock));
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

        assertNotNotified(investor);
    }

    @Test
    void whenMultipleInvestorsAreRegisteredThenNoIsNotified() {
        Investor john = John();
        Investor carl = Carl();

        stockMarket.registerInvestor(john);
        stockMarket.registerInvestor(carl);

        assertNotNotified(john);
        assertNotNotified(carl);
    }

    @Test
    void whenInvestorIsRegisteredAndNoMatchOnStockMarketThenInvestorIsNotNotified() {
        Investor investor = John();

        stockMarket.registerInvestor(investor);

        pushMultipleOrdersForStock(Stock.of(anyPrice()), anyCount());

        assertNotNotified(investor);
    }

    @Test
    void whenInvestorIsRegisteredAndMatchOnStockMarketThenInvestorIsNotified() {
        Investor investor = John();

        stockMarket.registerInvestor(investor);

        Stock stock = Stock.of(anyPrice());

        makeStockMatch(stock, anyPrice());

        assertNotified(investor);
    }

    @Test
    void whenInvestorIsRegisterButNoMatchOnStocksPriceThenInvestorIsNotNotified() {
        Investor investor = John();

        Stock stock = Stock.of(anyPrice());

        stockMarket.registerInvestor(investor);

        stockMarket.pushOrder(Order.purchase(Money.of(32), stock));
        stockMarket.pushOrder(Order.sell(Money.of(22), stock));

        assertNotNotified(investor);
    }

    @Test
    void whenMatchOccursLaterOnThenInvestorIsNotifiedAsWell() {
        Investor investor = John();

        Stock stock = Stock.of(anyPrice());

        stockMarket.registerInvestor(investor);

        stockMarket.pushOrder(Order.purchase(Money.of(32), stock));
        stockMarket.pushOrder(Order.sell(Money.of(12), stock));

        assertNotNotified(investor);

        stockMarket.pushOrder(Order.sell(Money.of(32), stock));

        assertNotified(investor);
    }

    @Test
    void investorShouldNotBeNotifiedTwiceIfRegisteredTwice() {
        Investor investor = John();

        Stock stock = Stock.of(anyPrice());

        stockMarket.registerInvestor(investor);
        stockMarket.registerInvestor(investor);

        makeStockMatch(stock, anyPrice());

        assertNotified(investor);
        assertNotNotified(investor);
    }

    @Test
    void whenMatchOccursTwiceForSameOrderThenInvestorIsNotNotified() {
        Investor investor = John();

        Stock stock = Stock.of(anyPrice());

        stockMarket.registerInvestor(investor);

        makeStockMatch(stock, anyPrice());

        assertNotified(investor);

        stockMarket.pushOrder(Order.sell(anyPrice(), stock));

        assertNotNotified(investor);
    }

    @Test
    void whenMatchOccursThenStockPriceIsChanged() {
        Stock stock = Stock.of(anyPrice());

        makeStockMatch(stock, anyPrice());

        assertEquals(anyPrice(), stock.getPrice());
    }

    @Test
    void whenMatchOccursThenStockPriceIsTheLastMatch() {
        Investor investor = John();

        Stock stock = Stock.of(anyPrice());

        stockMarket.registerInvestor(investor);

        makeStockMatch(stock, anyPrice());

        makeStockMatch(stock, Money.of(32));

        assertNotified(investor);

        assertEquals(Money.of(32), stock.getPrice());
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

    private Money anyPrice() {
        return Money.of(0);
    }

    private int anyCount() {
        return 10;
    }

    private void makeStockMatch(final Stock stock, final Money price) {
        stockMarket.pushOrder(Order.purchase(price, stock));
        stockMarket.pushOrder(Order.sell(price, stock));
    }

    private void assertNotNotified(final Investor investor) {
        assertFalse(investor.wasNotified());
    }

    private void assertNotified(final Investor investor) {
        assertTrue(investor.wasNotified());
    }
}
