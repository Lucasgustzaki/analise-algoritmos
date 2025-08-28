package io.stockmarket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public final class StockMarket {

    private final List<Order> orders;
    private final Map<Stock, Set<StockNotifiable>> stockToInvestors;

    public StockMarket() {
        orders = new ArrayList<>();
        stockToInvestors = new HashMap<>();
    }

    public void addNotifiable(final StockNotifiable stockNotifiable, final Stock stock) {
        stockToInvestors
            .computeIfAbsent(stock, k -> new HashSet<>())
            .add(stockNotifiable);
    }

    public void pushOrder(final Order order) {
        orders.add(order);

        Stock stock = order.getStock();

        if (anyMatchFor(order)) {
            notifyInvestorsAboutStockChange(stock);
        }
    }

    private boolean anyMatchFor(final Order order) {
        return order.isSell()
                ? findMatchFor(order, Order::isPurchase)
                : findMatchFor(order, Order::isSell);
    }

    private boolean findMatchFor(final Order orderToBeMatched, final Predicate<Order> orderType) {
        Optional<Order> orderToMatch = orders.stream()
                .filter(orderType)
                .filter(isSamePrice(orderToBeMatched))
                .findFirst();

        if (orderToMatch.isPresent()) {
            orderToMatch.get().updateStockPrice();
            orders.remove(orderToMatch.get());
            orders.remove(orderToBeMatched);
        }

        return orderToMatch.isPresent();
    }

    private Predicate<Order> isSamePrice(final Order anOrder) {
        return (order) -> anOrder.getPrice().equals(order.getPrice());
    }

    private void notifyInvestorsAboutStockChange(final Stock stock) {
        stockToInvestors
            .getOrDefault(stock, Collections.emptySet())
            .forEach(stockNotifiable -> stockNotifiable.notify(stock));
    }
}