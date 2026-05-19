package kafka.statistics;

import java.util.Map;

public class StatisticsResult {
    public int totalRecords;
    public int tempCount;
    public int humidityCount;
    public int pm25Count;
    public int pm10Count;
    public int filteredTempCount;
    public int filteredHumidityCount;
    public int filteredPmCount;

    public double avgTemp;
    public double minTemp = Double.NaN;
    public double maxTemp = Double.NaN;
    public int tempAbove25;

    public double avgHumidity;
    public double minHumidity = Double.NaN;
    public double maxHumidity = Double.NaN;

    public double avgPm25;
    public double maxPm25 = Double.NaN;
    public int pm25Above35;

    public double avgPm10;
    public double maxPm10 = Double.NaN;
    public int pm10Above50;

    public int uniqueLocations;
    public Map<Long, Integer> recordsPerLocation;
    public Map<Long, String> locationCoordinates;

    /**
     * Tworzy tekstową reprezentację wyniku, żeby łatwo podejrzeć wszystkie wyliczone pola.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Total records: ").append(totalRecords).append("\n");
        sb.append("Temperature - count: ").append(tempCount).append(", avg: ").append(avgTemp).append(", min: ").append(minTemp).append(", max: ").append(maxTemp).append(", >25C: ").append(tempAbove25).append("\n");
        sb.append("Humidity - count: ").append(humidityCount).append(", avg: ").append(avgHumidity).append(", min: ").append(minHumidity).append(", max: ").append(maxHumidity).append("\n");
        sb.append("PM2.5 - count: ").append(pm25Count).append(", avg: ").append(avgPm25).append(", max: ").append(maxPm25).append(", >35: ").append(pm25Above35).append("\n");
        sb.append("PM10 - count: ").append(pm10Count).append(", avg: ").append(avgPm10).append(", max: ").append(maxPm10).append(", >50: ").append(pm10Above50).append("\n");
        sb.append("Unique locations: ").append(uniqueLocations).append("\n");
        sb.append("Records per location: ").append(recordsPerLocation).append("\n");
        return sb.toString();
    }
}
