package io.stockmarket;

import java.util.*;
import java.util.function.Predicate;

public class Stock {

    private final List<Order> orders;
    private final Set<Investor> investors;

    public Stock() {
        this.investors = new HashSet<>();
        this.orders = new ArrayList<>();
    }

    public void registerInvestor(final Investor investor) {
        investors.add(investor);
    }

    public void pushOrder(final Order order) {
        orders.add(order);

        if (isMatch(order)) notifyInvestors();
    }

    private boolean isMatch(Order order) {
        return order.isSell()
                ? findMatch(order, Order::isPurchase)
                : findMatch(order, Order::isSell);
    }

    private boolean findMatch(Order purchaseOrder, Predicate<Order> isSell) {
        Optional<Order> sellOrder = orders.stream()
                .filter(isSell)
                .filter(isSamePrice(purchaseOrder))
                .findFirst();

        if (sellOrder.isPresent()) {
            orders.remove(sellOrder.get());
            orders.remove(purchaseOrder);
        }

        return sellOrder.isPresent();
    }

    private Predicate<Order> isSamePrice(Order sellOrder) {
        return (order) -> sellOrder.price() == order.price();
    }

    private void notifyInvestors() {
        investors.forEach(Investor::notifyOnStock);
    }
}
