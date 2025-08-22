package io.stockmarket;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Stock {

    private final List<Order> orders;
    private final List<Investor> investors;

    public Stock() {
        this.investors = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    public void registerInvestor(final Investor investor) {
        investors.add(investor);
    }

    public void pushOrder(final Order order) {
        if (isMatch(order)) notifyInvestors();
        else orders.add(order);
    }

    private boolean isMatch(Order order) {
        return order.isSell() ?
                isSellMatch(order) :
                isPurchaseMatch(order);
    }

    private boolean isSellMatch(Order sellOrder) {
        Optional<Order> purchaseOrder = orders.stream()
                .filter(Order::isPurchase)
                .filter(isSamePrice(sellOrder))
                .findFirst();

        return purchaseOrder.isPresent();
    }

    private boolean isPurchaseMatch(Order purchaseOrder) {
        Optional<Order> sellOrder = orders.stream()
                .filter(Order::isSell)
                .filter(isSamePrice(purchaseOrder))
                .findFirst();

        return sellOrder.isPresent();
    }

    private Predicate<Order> isSamePrice(Order sellOrder) {
        return (order) -> sellOrder.price() == order.price();
    }

    private void notifyInvestors() {
        investors.forEach(Investor::notifyOnStock);
    }
}
