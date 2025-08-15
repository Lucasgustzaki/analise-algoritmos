package io.library;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

public record Money(Currency currency, BigDecimal amount) {

    private static final Currency DEFAULT_CURRENCY = Currency.getInstance(Locale.getDefault());

    public Money(final BigDecimal amount) {
        this(DEFAULT_CURRENCY, amount);
    }

    public static Money zero() {
        return new Money(DEFAULT_CURRENCY, BigDecimal.ZERO);
    }
}
