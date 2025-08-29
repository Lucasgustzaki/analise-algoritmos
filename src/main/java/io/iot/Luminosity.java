package io.iot;

public final class Luminosity {

    private final int value;

    private Luminosity(int value) {
        this.value = value;
    }

    public static Luminosity of(int value) {
        return new Luminosity(value);
    }

    public int get() {
        return value;
    }
}
