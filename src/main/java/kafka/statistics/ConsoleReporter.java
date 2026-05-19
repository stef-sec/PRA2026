package kafka.statistics;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class ConsoleReporter {
    /**
     * Wypisuje statystyki do konsoli w czytelnym formacie, korzystając z gotowego obiektu wynikowego.
     */
    public void print(StatisticsResult r) {
        System.out.println("=== Air sensors statistics ===");
        System.out.printf("Total records read: %d\n", r.totalRecords);
        System.out.println();

        System.out.println("Temperature:");
        System.out.printf("  Count: %d (filtered: %d)\n", r.tempCount, r.filteredTempCount);
        System.out.printf("  Avg: %.2f C, Min: %.2f C, Max: %.2f C\n", r.avgTemp, r.minTemp, r.maxTemp);
        System.out.printf("  >25C: %d\n", r.tempAbove25);
        System.out.println();

        System.out.println("Humidity:");
        System.out.printf("  Count: %d (filtered: %d)\n", r.humidityCount, r.filteredHumidityCount);
        System.out.printf("  Avg: %.2f %%, Min: %.2f %%, Max: %.2f %%\n", r.avgHumidity, r.minHumidity, r.maxHumidity);
        System.out.println();

        System.out.println("PM2.5:");
        System.out.printf("  Count: %d, Avg: %.2f, Max: %.2f, >35: %d\n", r.pm25Count, r.avgPm25, r.maxPm25, r.pm25Above35);
        System.out.println();

        System.out.println("PM10:");
        System.out.printf("  Count: %d, Avg: %.2f, Max: %.2f, >50: %d\n", r.pm10Count, r.avgPm10, r.maxPm10, r.pm10Above50);
        System.out.println();

        System.out.printf("Unique locations: %d\n", r.uniqueLocations);
        System.out.println();

        System.out.println("Top locations by number of records:");
        r.recordsPerLocation.entrySet().stream()
                .sorted(Map.Entry.<Long,Integer>comparingByValue(Comparator.reverseOrder()))
                .limit(20)
                .forEach(e -> {
                    Long id = e.getKey();
                    Integer cnt = e.getValue();
                    String coord = (r.locationCoordinates != null) ? r.locationCoordinates.get(id) : null;
                    System.out.printf("  id=%d, records=%d, coords=%s\n", id, cnt, coord == null ? "n/a" : coord);
                });

        System.out.println();
        System.out.println("Records per location map size: " + (r.recordsPerLocation == null ? 0 : r.recordsPerLocation.size()));
    }
}
