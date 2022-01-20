package com.github.trohovsky.crypto.profit.io;

import com.github.trohovsky.crypto.profit.model.PortfolioEntry;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.NoSuchFileException;
import java.util.List;

import static com.github.trohovsky.crypto.profit.TestData.BTC;
import static com.github.trohovsky.crypto.profit.TestData.ETH;
import static com.github.trohovsky.crypto.profit.TestData.RSR;
import static com.github.trohovsky.crypto.profit.TestData.XRP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FilePortfolioReaderTest {

    @Test
    void readPortfolioReadsPortfolio() throws IOException {
        final FilePortfolioReader portfolioReader = new FilePortfolioReader("src/test/resources/correct_file.txt");

        final List<PortfolioEntry> portfolio = portfolioReader.readPortfolio();

        assertEquals(4, portfolio.size());
        assertEquals(new PortfolioEntry(BTC, BigDecimal.valueOf(10)), portfolio.get(0));
        assertEquals(new PortfolioEntry(ETH, BigDecimal.valueOf(0.5)), portfolio.get(1));
        assertEquals(new PortfolioEntry(XRP, BigDecimal.valueOf(2000.0)), portfolio.get(2));
        assertEquals(new PortfolioEntry(RSR, BigDecimal.valueOf(10_000)), portfolio.get(3));
    }

    @Test
    void readPortfolioReadsEmptyPortfolio() throws IOException {
        final FilePortfolioReader portfolioReader = new FilePortfolioReader("src/test/resources/empty_file.txt");

        final List<PortfolioEntry> portfolio = portfolioReader.readPortfolio();

        assertEquals(0, portfolio.size());
    }

    @Test
    void readPortfolioThrowsNoSuchFileExceptionForNonExistingFile() {
        final FilePortfolioReader portfolioReader = new FilePortfolioReader("non_existing_file.txt");

        assertThrows(NoSuchFileException.class, portfolioReader::readPortfolio);
    }

    @Test
    void readPortfolioThrowsIllegalArgumentExceptionForMalformedFile() {
        final FilePortfolioReader portfolioReader = new FilePortfolioReader("src/test/resources/malformed_file.txt");

        assertThrows(IllegalArgumentException.class, portfolioReader::readPortfolio, "Invalid entry: ETH=");
    }
}