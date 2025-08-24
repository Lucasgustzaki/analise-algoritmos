package io.stockmarket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StockNotificationTest {
    
    private Stock PETR4;
    private Stock ITUB4;

    private StockMarket stockMarket;

    @BeforeEach
    void setUp() {
        PETR4 = Stock.of("PETR4", anyPrice());
        ITUB4 = Stock.of("ITUB4", anyPrice());

        stockMarket = new StockMarket();
    }

    @Test
    void whenInvestorIsRegisteredAndMatchOnStockMarketThenInvestorIsNotified() {
        Investor investor = John();

        stockMarket.registerInvestorOnStock(investor, PETR4);

        makeStockMatch(PETR4, anyPrice());

        assertWasNotified(investor);
    }

    @Test
    void whenInvestorIsRegisteredOnMultipleStocksThenNotifyAboutAllIfMatches() {
        Investor investor = John();

        stockMarket.registerInvestorOnStock(investor, PETR4);
        stockMarket.registerInvestorOnStock(investor, ITUB4);

        makeStockMatch(PETR4, anyPrice());
        
        assertWasNotified(investor, PETR4);
        
        makeStockMatch(ITUB4, anyPrice());

        assertWasNotified(investor, ITUB4);
    }
    
    @Test
    void whenInvestorIsRegisteredOnMultipleStocksThenKeepNotificationOrderTheSameAsMatchOrder() {
        Investor investor = John();

        stockMarket.registerInvestorOnStock(investor, PETR4);
        stockMarket.registerInvestorOnStock(investor, ITUB4);

        makeStockMatch(PETR4, anyPrice());
        makeStockMatch(ITUB4, anyPrice());

        assertWasNotified(investor, ITUB4);
        assertWasNotified(investor, PETR4);
    }

    @Test
    void whenMultipleInvestorsAreRegisteredOnTheSameStockThenNotifyAll() {
        Investor john = John();

        stockMarket.registerInvestorOnStock(john, PETR4);

        Investor carl = Carl();

        stockMarket.registerInvestorOnStock(carl, PETR4);

        makeStockMatch(PETR4, anyPrice());

        assertWasNotified(john, PETR4);
        assertWasNotified(carl, PETR4);
    }
    
    @Test
    void whenInvestorIsRegisteredMultipleTimesForSameStockThenSendJustOneNotification() {
        Investor investor = John();

        stockMarket.registerInvestorOnStock(investor, PETR4);
        stockMarket.registerInvestorOnStock(investor, PETR4);

        makeStockMatch(PETR4, anyPrice());

        assertWasNotified(investor, PETR4);

        assertWasNotNotified(investor);
    }

    @Test
    void whenInvestorIsRegisteredButMatchOccursForDifferentStockThenIsNotNotified() {
        Investor investor = John();

        stockMarket.registerInvestorOnStock(investor, ITUB4);

        makeStockMatch(PETR4, anyPrice());

        assertWasNotNotified(investor);
    }

    @Test
    void whenInvestorIsRegisterButNoMatchOnStocksPriceThenInvestorIsNotNotified() {
        Investor investor = John();

        stockMarket.registerInvestorOnStock(investor, PETR4);

        stockMarket.pushOrder(Carl().newPurchaseOrder(Money.of(32), PETR4));
        stockMarket.pushOrder(Maria().newSellOrder(Money.of(22), PETR4));

        assertWasNotNotified(investor);
    }

    @Test
    void whenMatchOccursLaterOnThenInvestorIsNotifiedAsWell() {
        Investor investor = John();

        stockMarket.registerInvestorOnStock(investor, ITUB4);

        stockMarket.pushOrder(John().newPurchaseOrder(Money.of(32), ITUB4));
        stockMarket.pushOrder(Maria().newSellOrder(Money.of(12), ITUB4));

        assertWasNotNotified(investor);

        stockMarket.pushOrder(John().newSellOrder(Money.of(32), ITUB4));

        assertWasNotified(investor);
    }
    
    @Test
    void whenNoMatchOccursThenStockPriceKeepsTheSame() {
        Money initialPrice = PETR4.getPrice();

        stockMarket.pushOrder(John().newPurchaseOrder(Money.of(22), PETR4));
        stockMarket.pushOrder(Maria().newSellOrder(Money.of(18), PETR4));

        assertEquals(initialPrice, PETR4.getPrice());
    }

    @Test
    void whenMatchOccursThenStockPriceIsChanged() {
        Money newPrice = Money.of(18);

        makeStockMatch(PETR4, newPrice);

        assertEquals(newPrice, PETR4.getPrice());
    }

    @Test
    void whenMatchOccursThenStockPriceIsTheLastMatch() {
        Investor investor = John();

        stockMarket.registerInvestorOnStock(investor, PETR4);

        makeStockMatch(PETR4, Money.of(18));

        Money newPrice = Money.of(32);

        makeStockMatch(PETR4, newPrice);

        assertEquals(newPrice, PETR4.getPrice());
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

    private Investor anyInvestor() {
        return new Investor("Unknown");
    }

    private void makeStockMatch(final Stock stock, final Money price) {
        stockMarket.pushOrder(anyInvestor().newPurchaseOrder(price, stock));
        stockMarket.pushOrder(anyInvestor().newSellOrder(price, stock));
    }

    private void assertWasNotified(final Investor investor, final Stock stock) {
        assertEquals(stock, investor.getLastNotification().get().getStock());
    }

    private void assertWasNotNotified(final Investor investor) {
        assertTrue(investor.getLastNotification().isEmpty());
    }

    private void assertWasNotified(final Investor investor) {
        assertTrue(investor.getLastNotification().isPresent());
    }
}
