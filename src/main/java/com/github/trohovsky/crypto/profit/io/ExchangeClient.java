package com.github.trohovsky.crypto.profit.io;

import java.io.IOException;
import java.math.BigDecimal;

public interface ExchangeClient {

    BigDecimal getExchangeRate(String from, String to) throws IOException;
}
