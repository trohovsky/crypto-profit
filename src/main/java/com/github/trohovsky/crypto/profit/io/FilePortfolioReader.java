package com.github.trohovsky.crypto.profit.io;

import com.github.trohovsky.crypto.profit.model.PortfolioEntry;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FilePortfolioReader implements PortfolioReader {

    private final String portfolioFilePath;

    private static final Pattern ENTRY_PATTERN = Pattern.compile("\\s*([A-Z]+)\\s*=\\s*(\\d*\\.\\d+|\\d+\\.?\\d*)\\s*");

    public FilePortfolioReader(String portfolioFilePath) {
        this.portfolioFilePath = portfolioFilePath;
    }

    public List<PortfolioEntry> readPortfolio() throws IOException {
        final List<PortfolioEntry> portfolio = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Path.of(portfolioFilePath))) {
            lines.forEach(line -> {
                final Matcher matcher = ENTRY_PATTERN.matcher(line);
                if (!matcher.matches()) {
                    throw new IllegalArgumentException("Invalid entry: " + line);
                }
                portfolio.add(new PortfolioEntry(matcher.group(1), new BigDecimal(matcher.group(2))));
            });
        }
        return portfolio;
    }
}
