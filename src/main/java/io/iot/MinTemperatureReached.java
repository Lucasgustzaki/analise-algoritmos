package io.iot;

public class MinTemperatureReached extends RuntimeException {

    private static final String MESSAGE = "Minimum temperature reached.";

    public MinTemperatureReached() {
        super(MESSAGE);
    }
}
