package kafka.export;

import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.statistics.StatisticsResult;

import java.io.File;

public class JsonExporter {
    private final ObjectMapper mapper = new ObjectMapper();

    public void save(StatisticsResult result, String filePath) throws Exception {
        File f = new File(filePath);
        mapper.writerWithDefaultPrettyPrinter().writeValue(f, result);
    }
}
