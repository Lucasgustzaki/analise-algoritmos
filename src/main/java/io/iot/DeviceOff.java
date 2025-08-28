package io.iot;

public class DeviceOff extends RuntimeException {

    private static final String MESSAGE = "Cannot operate on off device.";

    public DeviceOff() {
        super(MESSAGE);
    }
}
