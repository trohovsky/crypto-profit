package com.github.trohovsky.crypto.profit.model;

import java.math.BigDecimal;
import java.util.Objects;

public class PortfolioEntry {

    private final String currency;
    private final BigDecimal quantity;

    public PortfolioEntry(String currency, BigDecimal quantity) {
        this.currency = currency;
        this.quantity = quantity;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PortfolioEntry that = (PortfolioEntry) o;
        return Objects.equals(getCurrency(), that.getCurrency()) && Objects.equals(getQuantity(), that.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCurrency(), getQuantity());
    }

    @Override
    public String toString() {
        return "PortfolioEntry{" +
                "currency='" + currency + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
