package com.github.trohovsky.crypto.profit.io;

import com.github.trohovsky.crypto.profit.model.PortfolioEntry;

import java.io.IOException;
import java.util.List;

public interface PortfolioReader {

    List<PortfolioEntry> readPortfolio() throws IOException;
}
