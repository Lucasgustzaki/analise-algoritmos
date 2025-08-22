package io.stockmarket;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public final class StockMarket {

    private final List<Order> orders;
    private final Set<Investor> investors;

    public StockMarket() {
        this.orders = new ArrayList<>();
        this.investors = new HashSet<>();
    }

    public void registerInvestor(final Investor investor) {
        investors.add(investor);
    }

    public void pushOrder(final Order order) {
        orders.add(order);

        if (anyMatchFor(order))
            notifyInvestorsAboutStockChange(order.getStock());
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
        return (order) -> anOrder.getPrice() == order.getPrice();
    }

    private void notifyInvestorsAboutStockChange(final Stock stock) {
        investors.forEach(investor -> investor.sendNotification(stock));
    }
}
