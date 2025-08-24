package io.stockmarket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StockTest {

    private StockMarket stockMarket;

    @BeforeEach
    void setUp() {
        stockMarket = new StockMarket();
    }

    @Test
    void whenInvestorIsRegisteredThenIsNotNotifiedAsNoUpdatesOnStockMarket() {
        Investor investor = John();

        stockMarket.registerInvestor(investor);

        assertWasNotNotified(investor);
    }

    @Test
    void whenMultipleInvestorsAreRegisteredThenNoOneIsNotifiedAsNoUpdatesOnStockMarket() {
        Investor john = John();
        Investor carl = Carl();

        stockMarket.registerInvestor(john);
        stockMarket.registerInvestor(carl);

        assertWasNotNotified(john);
        assertWasNotNotified(carl);
    }

    @Test
    void whenInvestorIsRegisteredAndMatchOnStockMarketThenInvestorIsNotified() {
        Investor investor = John();

        stockMarket.registerInvestor(investor);

        Stock stock = Stock.of(anyPrice());

        makeStockMatch(Carl(), stock, anyPrice());

        assertWasNotified(investor);
    }

    @Test
    void whenInvestorIsRegisterButNoMatchOnStocksPriceThenInvestorIsNotNotified() {
        Investor investor = John();

        stockMarket.registerInvestor(investor);

        Stock stock = Stock.of(anyPrice());

        stockMarket.pushOrder(Order.purchase(Carl(), Money.of(32), stock));
        stockMarket.pushOrder(Order.sell(Maria(), Money.of(22), stock));

        assertWasNotNotified(investor);
    }

    @Test
    void whenMatchOccursLaterOnThenInvestorIsNotifiedAsWell() {
        Investor investor = John();

        Stock stock = Stock.of(anyPrice());

        stockMarket.pushOrder(Order.purchase(Carl(), Money.of(32), stock));
        stockMarket.pushOrder(Order.sell(Maria(), Money.of(12), stock));

        assertWasNotNotified(investor);

        stockMarket.pushOrder(Order.sell(John(), Money.of(32), stock));

        assertWasNotified(investor);
    }

    @Test
    void whenInvestorIsRegisteredTwiceItShouldBeNotifiedJustOne() {
        Investor investor = John();

        stockMarket.registerInvestor(investor);
        stockMarket.registerInvestor(investor);

        Stock stock = Stock.of(anyPrice());

        makeStockMatch(Maria(), stock, anyPrice());

        assertWasNotified(investor);
        assertWasNotNotified(investor);
    }

    @Test
    void whenMatchOccursThenStockPriceIsChanged() {
        Stock stock = Stock.of(anyPrice());

        Money newPrice = Money.of(18);

        makeStockMatch(Carl(), stock, newPrice);

        assertEquals(newPrice, stock.getPrice());
    }

    @Test
    void whenMatchOccursThenStockPriceIsTheLastMatch() {
        Investor investor = John();

        stockMarket.registerInvestor(investor);

        Stock stock = Stock.of(anyPrice());

        makeStockMatch(Maria(), stock, Money.of(18));

        Money newPrice = Money.of(32);

        makeStockMatch(Carl(), stock, newPrice);

        assertEquals(newPrice, stock.getPrice());
    }

    private Investor John() {
        return new Investor("John");
    }

    private Investor Carl() {
        return new Investor("Carl");
    }

    private Investor Maria() {
        return new Investor("Maria");
    }

    private Money anyPrice() {
        return Money.of(0);
    }

    private void makeStockMatch(final Investor investor, final Stock stock, final Money price) {
        stockMarket.pushOrder(Order.purchase(investor, price, stock));
        stockMarket.pushOrder(Order.sell(investor, price, stock));
    }

    private void assertWasNotNotified(final Investor investor) {
        assertTrue(investor.getLastNotification().isEmpty());
    }

    private void assertWasNotified(final Investor investor) {
        assertTrue(investor.getLastNotification().isPresent());
    }
}
