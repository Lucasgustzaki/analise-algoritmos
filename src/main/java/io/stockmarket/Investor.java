package io.stockmarket;

import java.util.Stack;

public class Investor {

    private final Stack<StockMarketNotification> notifications;

    public Investor() {
        this.notifications = new Stack<>();
    }

    public boolean wasNotified() {
        if (notifications.isEmpty()) {
            return false;
        }

        notifications.pop();
        return true;
    }

    public void notifyOnStock() {
        notifications.push(new StockMarketNotification());
    }
}
