package io.library;

public class Weight {

    private static final Weight ZERO = Weight.grams(0);
    private static final String PRINT_FORMAT = "[ Weight %d GRAMS ]";

    private final int value;

    public static Weight grams(int grams) {
        return new Weight(grams);
    }

    private Weight(final int value) {
        this.value = value;
    }

    public static Weight zero() {
        return ZERO;
    }

    public Weight add(final Weight weight) {
        return new Weight(this.value + weight.value);
    }

    public Weight sub(final Weight weight) {
        return new Weight(this.value - weight.value);
    }

    public boolean lessThanOrEqualTo(final Weight other) {
        return this.value <= other.value;
    }

    public int countBlocks(final Weight grams) {
        return this.value / grams.value;
    }

    @Override
    public String toString() {
        return PRINT_FORMAT.formatted(value);
    }
}
