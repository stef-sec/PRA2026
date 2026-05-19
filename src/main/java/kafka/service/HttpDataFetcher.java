package kafka.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class HttpDataFetcher implements DataFetcher {
    @Override
    public InputStream fetch(String url) throws IOException {
        return new URL(url).openStream();
    }
}
