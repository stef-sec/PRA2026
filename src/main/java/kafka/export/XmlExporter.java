package kafka.export;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import kafka.statistics.StatisticsResult;

import java.io.File;

public class XmlExporter {
    private final XmlMapper mapper = new XmlMapper();

    /**
     * Zapisuje wynik jako XML, używając XmlMappera do automatycznej serializacji struktury danych.
     */
    public void save(StatisticsResult result, String filePath) throws Exception {
        File f = new File(filePath);
        mapper.writerWithDefaultPrettyPrinter().writeValue(f, result);
    }
}
