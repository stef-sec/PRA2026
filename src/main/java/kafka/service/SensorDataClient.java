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

    public SensorDataClient() {
        this.fetcher = new HttpDataFetcher();
    }

    // For tests / injection
    public SensorDataClient(DataFetcher fetcher) {
        this.fetcher = fetcher;
    }

    public List<Root> readFromFile(File file) throws IOException {
        try {
            return mapper.readValue(file, new TypeReference<List<Root>>(){});
        } catch (IOException e) {
            throw e;
        }
    }

    public List<Root> readFromUrl(String url) throws Exception {
        try (InputStream in = fetcher.fetch(url)) {
            return mapper.readValue(in, new TypeReference<List<Root>>(){});
        }
    }
}
