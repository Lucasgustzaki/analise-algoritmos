package io.iot;

public class MinTemperatureReachedException extends RuntimeException {

    private static final String MESSAGE = "Minimum temperature reached.";

    public MinTemperatureReachedException() {
        super(MESSAGE);
    }
}
