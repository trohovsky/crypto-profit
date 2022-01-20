package com.github.trohovsky.crypto.profit.io;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpExchangeClient implements ExchangeClient {

    private static final String EXCHANGE_URL_FORMAT = "https://min-api.cryptocompare.com/data/price?fsym=%s&tsyms=%s";
    private static final Pattern OK_RESPONSE_PATTERN = Pattern.compile("\\{\"[A-Z]+\":(\\d*\\.\\d+|\\d+\\.?\\d*)}");

    @Override
    public BigDecimal getExchangeRate(String from, String to) throws IOException {
        final String content = requestExchangeRate(from, to);
        final Matcher matcher = OK_RESPONSE_PATTERN.matcher(content.replaceAll("\\s", ""));
        if (!matcher.matches()) {
            throw new RuntimeException("Cannot get the exchange rate for " + from + "-" + to + ", content: " + content);
        }
        return new BigDecimal(matcher.group(1));
    }

    private static String requestExchangeRate(String from, String to) throws IOException {
        final HttpURLConnection urlConnection =
                (HttpURLConnection) new URL(String.format(EXCHANGE_URL_FORMAT, from, to)).openConnection();
        final String content = getContent(urlConnection);
        if (urlConnection.getResponseCode() != 200) {
            throw new RuntimeException("Status: " + urlConnection.getResponseCode() + ", content: " + content);
        }
        return content;
    }

    private static String getContent(HttpURLConnection urlConnection) throws IOException {
        try (Scanner scanner =
                     new Scanner(urlConnection.getInputStream(), StandardCharsets.UTF_8.name()).useDelimiter("\\A")) {
            return scanner.hasNext() ? scanner.next() : "";
        }
    }
}
