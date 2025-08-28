package io.stockmarket;

/**
 * Represents an observer that can be notified about {@link Stock} updates.
 */
public interface StockNotifiable {

    void notify(final Stock stock);
}
