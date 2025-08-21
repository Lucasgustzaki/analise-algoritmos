package io.stockmarket;

import java.util.ArrayList;
import java.util.List;

public class Stock {

    private final List<Order> orders;

    public Stock() {
        this.orders = new ArrayList<>();
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

    private boolean isSellMatch(Order order) {
        return false;
    }

    private boolean isPurchaseMatch(Order order) {
        return false;
    }

    private void notifyInvestors() {
    }

    public void registerInvestor(final Investor investor) {
    }
}
