package kafka.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class HttpDataFetcher implements DataFetcher {
    /**
     * Otwiera strumień z adresu URL, korzystając z natywnego mechanizmu Javy do pobierania danych.
     */
    @Override
    public InputStream fetch(String url) throws IOException {
        return new URL(url).openStream();
    }
}
