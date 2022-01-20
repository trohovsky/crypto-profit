package com.github.trohovsky.crypto.profit.io;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static com.github.trohovsky.crypto.profit.TestData.BTC;
import static com.github.trohovsky.crypto.profit.TestData.EUR;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HttpExchangeClientTest {

    private final HttpExchangeClient exchangeClient = new HttpExchangeClient();

    @Test
    void getExchangeRateReturnsExchangeRate() throws IOException {
        assertTrue(exchangeClient.getExchangeRate(BTC, EUR).compareTo(BigDecimal.ZERO) >= 0);
    }

    @Test
    void getExchangeRateThrowsRuntimeExceptionForNonExistingCurrency() {
        assertThrows(RuntimeException.class, () -> exchangeClient.getExchangeRate("NON_EXISTING_CURRENCY", EUR));
    }
}