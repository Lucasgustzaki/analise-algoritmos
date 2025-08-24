package io.stockmarket;

import java.util.Optional;
import java.util.Stack;

public final class Investor {

    private final String name;
    private final Stack<Notification> notifications;

    public Investor(final String name) {
        this.name = name;
        this.notifications = new Stack<>();
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
}
