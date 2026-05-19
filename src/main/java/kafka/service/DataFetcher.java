package kafka.service;

import java.io.IOException;
import java.io.InputStream;

public interface DataFetcher {
    InputStream fetch(String url) throws IOException;
}
