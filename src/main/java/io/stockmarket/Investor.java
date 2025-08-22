package io.stockmarket;

import java.util.Stack;

public final class Investor {

    private final Stack<Notification> notifications;

    public Investor() {
        this.notifications = new Stack<>();
    }

    public void sendNotification(final Stock stock) {
        notifications.push(new Notification(stock));
    }

    public boolean wasNotified() {
        if (notifications.isEmpty()) {
            return false;
        }

        notifications.pop();

        return true;
    }
}
