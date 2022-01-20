package com.github.trohovsky.crypto.profit;

import com.github.trohovsky.crypto.profit.io.FilePortfolioReader;
import com.github.trohovsky.crypto.profit.io.PortfolioReader;
import com.github.trohovsky.crypto.profit.io.PortfolioReporter;
import com.github.trohovsky.crypto.profit.io.PrintStreamPortfolioReporter;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CryptoProfitAppTest {

    @Test
    void runReportsRatedPortfolio() {
        final PortfolioReader portfolioReader = new FilePortfolioReader("src/test/resources/bobs_crypto.txt");
        final PortfolioRater portfolioRater = new PortfolioRater((from, to) -> BigDecimal.TEN);
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final PortfolioReporter portfolioReporter = new PrintStreamPortfolioReporter(new PrintStream(out));
        final CryptoProfitApp cryptoProfitApp = new CryptoProfitApp(portfolioReader, portfolioRater, portfolioReporter);

        cryptoProfitApp.run();

        assertEquals("10.00 BTC = 100.00 EUR\n" +
                "5.00 ETH = 50.00 EUR\n" +
                "2000.00 XRP = 20000.00 EUR\n" +
                "Total: 20150.00 EUR\n", out.toString());
    }
}