package kafka.service;

import java.io.IOException;
import java.io.InputStream;

public interface DataFetcher {
    /**
     * Pobiera strumień danych spod wskazanego adresu, aby można go było łatwo podmienić w testach.
     */
    InputStream fetch(String url) throws IOException;
}
