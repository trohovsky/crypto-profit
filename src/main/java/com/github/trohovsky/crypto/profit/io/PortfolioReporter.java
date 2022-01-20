package com.github.trohovsky.crypto.profit.io;

import com.github.trohovsky.crypto.profit.model.RatedPortfolio;

public interface PortfolioReporter {

    void report(RatedPortfolio portfolio);
}
