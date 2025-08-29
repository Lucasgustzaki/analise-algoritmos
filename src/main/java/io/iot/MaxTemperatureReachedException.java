package io.iot;

public final class MaxTemperatureReachedException extends RuntimeException {

    private static final String MESSAGE = "Maximum temperature reached.";

    public MaxTemperatureReachedException() {
        super(MESSAGE);
    }
}
