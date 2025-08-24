package io.stockmarket;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;

public final class Money {

    private static final String PRINT_FORMAT = "[ Money %s %.2f ]";

    private static final Currency DEFAULT_CURRENCY = Currency.getInstance(Locale.getDefault());

    private final Currency currency;
    private final BigDecimal amount;

    private Money(final Currency currency, final BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public static Money of(final double amount) {
        return new Money(DEFAULT_CURRENCY, BigDecimal.valueOf(amount));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Money) obj;
        return Objects.equals(this.currency, that.currency) &&
                Objects.equals(this.amount, that.amount);
    }

    @Override
    public String toString() {
        return PRINT_FORMAT.formatted(currency.getSymbol(), amount);
    }
}

