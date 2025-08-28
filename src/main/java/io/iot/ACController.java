package io.iot;

public interface ACController extends Device {

    void increaseTemperature();

    void decreaseTemperature();

    void setTemperature(Temperature temperature);
}
