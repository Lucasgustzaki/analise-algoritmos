package io.stockmarket;

import java.util.*;
import java.util.function.Predicate;

public final class StockMarket {

    private final List<Order> orders;
    private final Map<Investor, Set<Stock>> investors;

    public StockMarket() {
        orders = new ArrayList<>();
        investors = new HashMap<>();
    }

    public void registerInvestorOnStock(final Investor investor, final Stock stock) {
        if (investors.containsKey(investor)) {
            investors.get(investor)
                    .add(stock);

            return;
        }

        investors.put(investor, Set.of(stock));
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
        getInvestorsRegisteredOn(stock).forEach(investor -> investor.sendNotification(stock));
    }

    private List<Investor> getInvestorsRegisteredOn(final Stock stock) {
        return investors.entrySet().stream()
                .filter(entry -> entry.getValue().contains(stock))
                .map(Map.Entry::getKey)
                .toList();
    }
}
