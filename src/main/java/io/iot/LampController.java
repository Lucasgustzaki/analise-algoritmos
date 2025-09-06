package io.iot;

public interface LampController {

    /**
     * Checks if the lamp is on.
     *
     * @return true if the lamp is on, false otherwise.
     */
    boolean isOn();

    /**
     * Turns the lamp on.
     */
    void on();

    /**
     * Turns the lamp off.
     */
    void off();
}
