package com.github.trohovsky.crypto.profit.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class RatedPortfolio {

    private final String currency;
    private final List<RatedPortfolioEntry> entries = new ArrayList<>();
    private BigDecimal totalValue = BigDecimal.ZERO;

    public RatedPortfolio(String currency) {
        this.currency = currency;
    }

    public void addEntry(RatedPortfolioEntry entry) {
        entries.add(entry);
        totalValue = totalValue.add(entry.getValue());
    }

    public List<RatedPortfolioEntry> getEntries() {
        return Collections.unmodifiableList(entries);
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final RatedPortfolio that = (RatedPortfolio) o;
        return Objects.equals(getCurrency(), that.getCurrency()) && Objects.equals(getEntries(), that.getEntries())
                && Objects.equals(getTotalValue(), that.getTotalValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCurrency(), getEntries(), getTotalValue());
    }

    @Override
    public String toString() {
        return "RatedPortfolio{" +
                "currency='" + currency + '\'' +
                ", entries=" + entries +
                ", totalValue=" + totalValue +
                '}';
    }
}
