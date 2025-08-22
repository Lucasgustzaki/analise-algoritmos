package io.stockmarket;

public class Investor {

    private boolean notified;

    public boolean wasNotified() {
        return notified;
    }

    public void notifyOnStock() {
        this.notified = true;
    }
}
