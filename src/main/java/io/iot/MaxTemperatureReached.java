package io.iot;

public final class MaxTemperatureReached extends RuntimeException {

    private static final String MESSAGE = "Maximum temperature reached.";

    public MaxTemperatureReached() {
        super(MESSAGE);
    }
}
