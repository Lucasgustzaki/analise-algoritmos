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
        return new Temperature(25);
    }

    public Temperature increaseBy(final int toIncrease) {
        return new Temperature(this.Celsius + toIncrease);
    }

    public int get() {
        return Celsius;
    }

    public Temperature decreaseBy(final int toDecrease) {
        return new Temperature(this.Celsius - toDecrease);
    }

    public boolean isDefault() {
        return this.Celsius == 25;
    }

    public boolean isHigherThan(final Temperature some) {
        return this.Celsius > some.Celsius;
    }

    public boolean isLowerThan(final Temperature some) {
        return this.Celsius < some.Celsius;
    }
}
