package com.github.trohovsky.crypto.profit.io;

import com.github.trohovsky.crypto.profit.model.PortfolioEntry;
import com.github.trohovsky.crypto.profit.model.RatedPortfolio;
import com.github.trohovsky.crypto.profit.model.RatedPortfolioEntry;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static com.github.trohovsky.crypto.profit.TestData.BTC;
import static com.github.trohovsky.crypto.profit.TestData.ETH;
import static com.github.trohovsky.crypto.profit.TestData.EUR;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PrintStreamPortfolioReporterTest {

    @Test
    void reportPortfolioWritesPortfolioToPrintStream() {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final PrintStreamPortfolioReporter portfolioReporter = new PrintStreamPortfolioReporter(new PrintStream(out));
        final RatedPortfolio ratedPortfolio = new RatedPortfolio(EUR);
        ratedPortfolio.addEntry(new RatedPortfolioEntry(new PortfolioEntry(BTC, BigDecimal.valueOf(10)),
                BigDecimal.ONE));
        ratedPortfolio.addEntry(new RatedPortfolioEntry(new PortfolioEntry(ETH, BigDecimal.valueOf(0.5)),
                BigDecimal.TEN));

        portfolioReporter.report(ratedPortfolio);

        assertEquals("10.00 BTC = 10.00 EUR\n" +
                "0.50 ETH = 5.00 EUR\n" +
                "Total: 15.00 EUR\n", out.toString());
    }
}