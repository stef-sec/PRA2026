package kafka.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.model.Root;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class SensorDataClient {
    private final ObjectMapper mapper = new ObjectMapper();

    public List<Root> readFromFile(File file) throws Exception {
        return mapper.readValue(file, new TypeReference<List<Root>>(){});
    }

    public List<Root> readFromUrl(String url) throws Exception {
        try (InputStream in = new URL(url).openStream()) {
            return mapper.readValue(in, new TypeReference<List<Root>>(){});
        }
    }
}
