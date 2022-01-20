package com.github.trohovsky.crypto.profit.io;

import com.github.trohovsky.crypto.profit.model.RatedPortfolio;

import java.io.PrintStream;

public class PrintStreamPortfolioReporter implements PortfolioReporter {

    private final PrintStream printStream;

    public PrintStreamPortfolioReporter(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void report(RatedPortfolio portfolio) {
        portfolio.getEntries().forEach(entry -> {
            printStream.printf("%.2f %s = %.2f %s\n", entry.getPortfolioEntry().getQuantity(),
                    entry.getPortfolioEntry().getCurrency(), entry.getValue(), portfolio.getCurrency());
        });
        printStream.printf("Total: %.2f %s\n", portfolio.getTotalValue(), portfolio.getCurrency());
    }
}
