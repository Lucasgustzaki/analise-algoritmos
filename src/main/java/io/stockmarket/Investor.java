package io.stockmarket;

public class Investor {

    private boolean notified;

    public boolean wasNotified() {
        return notified;
    }

    public void notify(Stock stock) {
        notified = true;
    }
}
