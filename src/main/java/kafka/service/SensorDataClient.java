package kafka.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.model.Root;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SensorDataClient {
    private final ObjectMapper mapper = new ObjectMapper();
    private final DataFetcher fetcher;

    /**
     * Tworzy klienta z domyślnym pobieraniem przez HTTP, żeby program działał bez konfiguracji.
     */
    public SensorDataClient() {
        this.fetcher = new HttpDataFetcher();
    }

    /**
     * Tworzy klienta z wstrzykniętym źródłem danych, co pozwala łatwo podmieniać HTTP na mock w testach.
     */
    public SensorDataClient(DataFetcher fetcher) {
        this.fetcher = fetcher;
    }

    /**
     * Czyta dane z pliku i zamienia JSON na listę obiektów Root przy pomocy Jacksona.
     */
    public List<Root> readFromFile(File file) throws IOException {
        try {
            return mapper.readValue(file, new TypeReference<List<Root>>(){});
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Czyta dane z URL przez dostarczony fetcher, dzięki czemu logika pobierania jest odseparowana od parsowania.
     */
    public List<Root> readFromUrl(String url) throws Exception {
        try (InputStream in = fetcher.fetch(url)) {
            return mapper.readValue(in, new TypeReference<List<Root>>(){});
        }
    }
}
