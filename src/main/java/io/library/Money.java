package io.library;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;

public final class Money {

    private static final Currency DEFAULT_CURRENCY = Currency.getInstance(Locale.getDefault());
    private static final String PRINT_FORMAT = "[ Money %s %.2f ]";

    private final Currency currency;
    private final BigDecimal amount;

    public Money(Currency currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public Money(final BigDecimal amount) {
        this(DEFAULT_CURRENCY, amount);
    }

    public static Money of(final double amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money zero() {
        return new Money(DEFAULT_CURRENCY, BigDecimal.ZERO);
    }

    public Money add(final Money toAdd) {
        return new Money(this.currency, this.amount.add(toAdd.amount));
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
