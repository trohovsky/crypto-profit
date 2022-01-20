package com.github.trohovsky.crypto.profit;

import com.github.trohovsky.crypto.profit.io.ExchangeClient;
import com.github.trohovsky.crypto.profit.model.PortfolioEntry;
import com.github.trohovsky.crypto.profit.model.RatedPortfolio;
import com.github.trohovsky.crypto.profit.model.RatedPortfolioEntry;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class PortfolioRater {

    private final ExchangeClient exchangeClient;

    public PortfolioRater(ExchangeClient exchangeClient) {
        this.exchangeClient = exchangeClient;
    }

    public RatedPortfolio ratePortfolio(List<PortfolioEntry> portfolio, String currency) throws IOException {
        final RatedPortfolio ratedPortfolio = new RatedPortfolio(currency);
        for (PortfolioEntry entry : portfolio) {
            final BigDecimal exchangeRate = exchangeClient.getExchangeRate(entry.getCurrency(), currency);
            ratedPortfolio.addEntry(new RatedPortfolioEntry(entry, exchangeRate));
        }
        return ratedPortfolio;
    }
}
