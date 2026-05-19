package kafka.export;

import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.statistics.StatisticsResult;

import java.io.File;

public class JsonExporter {
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Zapisuje wynik jako JSON, ponieważ Jackson potrafi bez ręcznej serializacji zamienić obiekt na plik.
     */
    public void save(StatisticsResult result, String filePath) throws Exception {
        File f = new File(filePath);
        mapper.writerWithDefaultPrettyPrinter().writeValue(f, result);
    }
}
