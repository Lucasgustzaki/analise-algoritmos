package io.iot;

public class DeviceOffException extends RuntimeException {

    private static final String MESSAGE = "Cannot operate on off device.";

    public DeviceOffException() {
        super(MESSAGE);
    }
}
