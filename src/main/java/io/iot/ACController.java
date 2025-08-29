package io.iot;

public interface ACController {

    /**
     * Checks if the AC is currently on.
     *
     * @return true if the AC is on, false otherwise.
     */
    boolean isOn();

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

    /**
     * Gets the current temperature setting of the AC.
     *
     * @return The current temperature.
     */
    Temperature getTemperature();
}
