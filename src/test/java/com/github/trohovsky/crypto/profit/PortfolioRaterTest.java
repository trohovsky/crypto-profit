package com.github.trohovsky.crypto.profit;

import com.github.trohovsky.crypto.profit.model.PortfolioEntry;
import com.github.trohovsky.crypto.profit.model.RatedPortfolio;
import com.github.trohovsky.crypto.profit.model.RatedPortfolioEntry;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static com.github.trohovsky.crypto.profit.TestData.BTC;
import static com.github.trohovsky.crypto.profit.TestData.ETH;
import static com.github.trohovsky.crypto.profit.TestData.EUR;
import static com.github.trohovsky.crypto.profit.TestData.XRP;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PortfolioRaterTest {

    private static final BigDecimal EXCHANGE_RATE = BigDecimal.TEN;
    public static final List<PortfolioEntry> PORTFOLIO = List.of(
            new PortfolioEntry(BTC, BigDecimal.valueOf(10)),
            new PortfolioEntry(ETH, BigDecimal.valueOf(0.5)),
            new PortfolioEntry(XRP, BigDecimal.valueOf(2000.0)));

    @Test
    void ratePortfolioReturnsRatedPortfolio() throws IOException {
        final PortfolioRater portfolioRater = new PortfolioRater((from, to) -> EXCHANGE_RATE);

        final RatedPortfolio ratedPortfolio = portfolioRater.ratePortfolio(PORTFOLIO, EUR);

        assertEquals(3, ratedPortfolio.getEntries().size());
        assertEquals(new RatedPortfolioEntry(PORTFOLIO.get(0), EXCHANGE_RATE), ratedPortfolio.getEntries().get(0));
        assertEquals(new RatedPortfolioEntry(PORTFOLIO.get(1), EXCHANGE_RATE), ratedPortfolio.getEntries().get(1));
        assertEquals(new RatedPortfolioEntry(PORTFOLIO.get(2), EXCHANGE_RATE), ratedPortfolio.getEntries().get(2));
        assertEquals(BigDecimal.valueOf(20105.0), ratedPortfolio.getTotalValue());
    }

    @Test
    void ratePortfolioReturnsRatedPortfolioForEmptyPortfolio() throws IOException {
        final PortfolioRater portfolioRater = new PortfolioRater((from, to) -> EXCHANGE_RATE);

        final RatedPortfolio ratedPortfolio = portfolioRater.ratePortfolio(emptyList(), EUR);

        assertEquals(0, ratedPortfolio.getEntries().size());
        assertEquals(BigDecimal.ZERO, ratedPortfolio.getTotalValue());
    }

    @Test
    void ratePortfolioThrowsIOExceptionIfExchangeClientThrowsIt() {
        final PortfolioRater portfolioRater = new PortfolioRater((from, to) -> {
            throw new IOException();
        });

        assertThrows(IOException.class, () -> portfolioRater.ratePortfolio(PORTFOLIO, EUR));
    }
}