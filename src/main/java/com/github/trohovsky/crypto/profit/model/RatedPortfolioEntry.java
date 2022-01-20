package com.github.trohovsky.crypto.profit.model;

import java.math.BigDecimal;
import java.util.Objects;

public class RatedPortfolioEntry {

    private final PortfolioEntry portfolioEntry;
    private final BigDecimal rate;
    private final BigDecimal value;

    public RatedPortfolioEntry(PortfolioEntry portfolioEntry, BigDecimal rate) {
        this.portfolioEntry = portfolioEntry;
        this.rate = rate;
        this.value = portfolioEntry.getQuantity().multiply(rate);
    }

    public PortfolioEntry getPortfolioEntry() {
        return portfolioEntry;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final RatedPortfolioEntry that = (RatedPortfolioEntry) o;
        return Objects.equals(getPortfolioEntry(), that.getPortfolioEntry())
                && Objects.equals(getRate(), that.getRate()) && Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPortfolioEntry(), getRate(), getValue());
    }

    @Override
    public String toString() {
        return "RatedPortfolioEntry{" +
                "portfolioEntry=" + portfolioEntry +
                ", rate=" + rate +
                ", value=" + value +
                '}';
    }
}
