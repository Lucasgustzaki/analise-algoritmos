package io.iot;

public interface BlindController {

    /**
     * Returns whether the blinds are open.
     *
     * @return true if the blinds are open, false otherwise.
     */
    boolean isOpen();

    /**
     * Opens the blinds.
     */
    void open();

    /**
     * Closes the blinds.
     */
    void close();
}
