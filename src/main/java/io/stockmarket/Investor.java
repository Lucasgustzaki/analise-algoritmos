package io.stockmarket;

import java.util.Objects;
import java.util.Optional;
import java.util.Stack;

public final class Investor {

    private final String name;
    private final Stack<Notification> notifications;

    public Investor(final String name) {
        this.name = name;
        this.notifications = new Stack<>();
    }

    public Order newSellOrder(final Money money, final Stock stock) {
        return Order.sell(this, money, stock);
    }

    public Order newPurchaseOrder(final Money money, final Stock stock) {
        return Order.purchase(this, money, stock);
    }

    public void sendNotification(final Stock stock) {
        Notification notification = new Notification(stock);

        notifications.push(notification);
    }

    public Optional<Notification> getLastNotification() {
        if  (notifications.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(notifications.pop());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Investor investor = (Investor) o;
        return Objects.equals(name, investor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
