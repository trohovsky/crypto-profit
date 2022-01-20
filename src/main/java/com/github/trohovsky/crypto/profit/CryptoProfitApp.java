package com.github.trohovsky.crypto.profit;

import com.github.trohovsky.crypto.profit.io.HttpExchangeClient;
import com.github.trohovsky.crypto.profit.io.FilePortfolioReader;
import com.github.trohovsky.crypto.profit.io.PortfolioReader;
import com.github.trohovsky.crypto.profit.io.PortfolioReporter;
import com.github.trohovsky.crypto.profit.io.PrintStreamPortfolioReporter;
import com.github.trohovsky.crypto.profit.model.PortfolioEntry;
import com.github.trohovsky.crypto.profit.model.RatedPortfolio;

import java.util.Arrays;
import java.util.List;

public class CryptoProfitApp {

    private final PortfolioReader portfolioReader;
    private final PortfolioRater portfolioRater;
    private final PortfolioReporter portfolioReporter;

    public CryptoProfitApp(
            PortfolioReader portfolioReader,
            PortfolioRater portfolioRater,
            PortfolioReporter portfolioReporter
    ) {
        this.portfolioReader = portfolioReader;
        this.portfolioRater = portfolioRater;
        this.portfolioReporter = portfolioReporter;
    }

    public static void main(String[] args) {
        createCryptoProfitApp().run();
    }

    private static CryptoProfitApp createCryptoProfitApp() {
        final FilePortfolioReader portfolioReader = new FilePortfolioReader("bobs_crypto.txt");
        final PortfolioRater portfolioRater = new PortfolioRater(new HttpExchangeClient());
        final PortfolioReporter portfolioReporter = new PrintStreamPortfolioReporter(System.out);
        return new CryptoProfitApp(portfolioReader, portfolioRater, portfolioReporter);
    }

    public void run() {
        try {
            final List<PortfolioEntry> portfolio = portfolioReader.readPortfolio();
            final RatedPortfolio ratedPortfolio = portfolioRater.ratePortfolio(portfolio, "EUR");
            portfolioReporter.report(ratedPortfolio);
        } catch (Exception e) {
            System.err.println("An error occurred: " + e);
        }
    }
}
