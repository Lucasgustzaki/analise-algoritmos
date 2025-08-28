package io.iot;

public interface ACController {

    /**
     * Turns the AC on.
     */
    void on();

    /**
     * Turns the AC off.
     */
    void off();

    /**
     * Increases the temperature by a fixed interval.
     */
    void increaseTemperature();

    /**
     * Decreases the temperature by a fixed interval.
     */
    void decreaseTemperature();

    /**
     * Sets the temperature to a specific value.
     *
     * @param temperature The desired temperature.
     */
    void setTemperature(final Temperature temperature);
}
