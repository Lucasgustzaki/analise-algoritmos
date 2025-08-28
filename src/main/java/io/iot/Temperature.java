package io.iot;

public final class Temperature {

    private final int Celsius;

    private Temperature(int value) {
        this.Celsius = value;
    }

    public static Temperature of(int value) {
        return new Temperature(value);
    }

    public static Temperature getDefault() {
        return new Temperature(22);
    }

    public Temperature increaseBy(int toIncrease) {
        return new Temperature(this.Celsius + toIncrease);
    }

    public int get() {
        return Celsius;
    }

    public Temperature decreaseBy(int toDecrease) {
        return new Temperature(this.Celsius - toDecrease);
    }
}
