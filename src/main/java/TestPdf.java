import kafka.export.PdfExporter;
import kafka.statistics.StatisticsResult;

public class TestPdf {
    /**
     * Szybko generuje przykładowy PDF, żeby ręcznie sprawdzić eksport bez uruchamiania całego programu.
     */
    public static void main(String[] args) throws Exception {
        StatisticsResult r = new StatisticsResult();
        r.totalRecords = 3;
        r.tempCount = 2; r.filteredTempCount = 0; r.avgTemp = 12.34; r.minTemp = 10.0; r.maxTemp = 14.0; r.tempAbove25 = 0;
        r.humidityCount = 2; r.filteredHumidityCount = 0; r.avgHumidity = 50.0; r.minHumidity = 45.0; r.maxHumidity = 55.0;
        r.pm25Count = 2; r.avgPm25 = 5.5; r.maxPm25 = 6.0; r.pm25Above35 = 0;
        r.pm10Count = 2; r.avgPm10 = 7.5; r.maxPm10 = 8.0; r.pm10Above50 = 0;
        r.uniqueLocations = 2;

        PdfExporter pe = new PdfExporter();
        pe.save(r, "report_test.pdf");
        System.out.println("Saved report_test.pdf");
    }
}
