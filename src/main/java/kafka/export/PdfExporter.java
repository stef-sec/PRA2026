package kafka.export;

import kafka.statistics.StatisticsResult;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;

public class PdfExporter {
    public void save(StatisticsResult r, String filePath) throws Exception {
        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage();
            doc.addPage(page);

            try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {
                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
                cs.newLineAtOffset(50, 750);
                cs.showText("Air sensors statistics");
                cs.newLineAtOffset(0, -20);

                cs.setFont(PDType1Font.HELVETICA, 12);
                cs.showText("Total records read: " + r.totalRecords);
                cs.newLineAtOffset(0, -16);

                cs.showText(String.format("Temperature: count=%d (filtered=%d), avg=%.2f C, min=%.2f, max=%.2f, >25C=%d", r.tempCount, r.filteredTempCount, r.avgTemp, r.minTemp, r.maxTemp, r.tempAbove25));
                cs.newLineAtOffset(0, -16);

                cs.showText(String.format("Humidity: count=%d (filtered=%d), avg=%.2f %%, min=%.2f, max=%.2f", r.humidityCount, r.filteredHumidityCount, r.avgHumidity, r.minHumidity, r.maxHumidity));
                cs.newLineAtOffset(0, -16);

                cs.showText(String.format("PM2.5: count=%d, avg=%.2f, max=%.2f, >35=%d", r.pm25Count, r.avgPm25, r.maxPm25, r.pm25Above35));
                cs.newLineAtOffset(0, -16);

                cs.showText(String.format("PM10: count=%d, avg=%.2f, max=%.2f, >50=%d", r.pm10Count, r.avgPm10, r.maxPm10, r.pm10Above50));
                cs.newLineAtOffset(0, -16);

                cs.showText("Unique locations: " + r.uniqueLocations);
                cs.newLineAtOffset(0, -20);

                cs.showText("Top locations:");
                cs.newLineAtOffset(0, -16);
                // write top 10 locations
                int written = 0;
                for (var e : r.recordsPerLocation.entrySet()) {
                    if (written >= 10) break;
                    long id = e.getKey();
                    int cnt = e.getValue();
                    String coord = (r.locationCoordinates != null) ? r.locationCoordinates.get(id) : "n/a";
                    cs.showText(String.format("id=%d, records=%d, coords=%s", id, cnt, coord));
                    cs.newLineAtOffset(0, -14);
                    written++;
                }

                cs.endText();
            }

            File out = new File(filePath);
            doc.save(out);
        }
    }
}
