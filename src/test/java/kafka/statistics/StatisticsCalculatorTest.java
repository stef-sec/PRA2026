package kafka.statistics;

import kafka.model.Location;
import kafka.model.Root;
import kafka.model.SensorDataValue;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StatisticsCalculatorTest {

    @Test
    void computeAggregates_andFiltersInvalidValues() {
        StatisticsCalculator calculator = new StatisticsCalculator();

        List<Root> records = Arrays.asList(
                record(10, "52.2297", "21.0122",
                        value("temperature", "20"),
                        value("humidity", "40"),
                        value("temperature", "70"),
                        value("P1", "55"),
                        value("P2", "15")),
                record(11, "52.2300", "21.0100",
                        value("temperature", "30"),
                        value("humidity", "120"),
                        value("P1", "11"),
                        value("P2", "5")),
                record(10, "52.2297", "21.0122",
                        value("temperature", "25"))
        );

        StatisticsResult result = calculator.compute(records);

        assertEquals(3, result.totalRecords);
        assertEquals(3, result.tempCount);
        assertEquals(1, result.filteredTempCount);
        assertEquals(25.0, result.avgTemp, 0.0001);
        assertEquals(20.0, result.minTemp, 0.0001);
        assertEquals(30.0, result.maxTemp, 0.0001);
        assertEquals(1, result.tempAbove25);

        assertEquals(1, result.humidityCount);
        assertEquals(1, result.filteredHumidityCount);
        assertEquals(40.0, result.avgHumidity, 0.0001);
        assertEquals(40.0, result.minHumidity, 0.0001);
        assertEquals(40.0, result.maxHumidity, 0.0001);

        assertEquals(2, result.pm10Count);
        assertEquals(33.0, result.avgPm10, 0.0001);
        assertEquals(55.0, result.maxPm10, 0.0001);
        assertEquals(1, result.pm10Above50);

        assertEquals(2, result.pm25Count);
        assertEquals(10.0, result.avgPm25, 0.0001);
        assertEquals(15.0, result.maxPm25, 0.0001);
        assertEquals(0, result.pm25Above35);

        assertEquals(2, result.uniqueLocations);
        assertEquals(2, result.recordsPerLocation.size());
        assertEquals(2, result.recordsPerLocation.get(10L));
        assertEquals(1, result.recordsPerLocation.get(11L));
        assertEquals("52.2297, 21.0122", result.locationCoordinates.get(10L));
        assertEquals("52.2300, 21.0100", result.locationCoordinates.get(11L));
        assertEquals(0, result.filteredPmCount);
    }

    @Test
    void computeEmptyList_returnsEmptyStats() {
        StatisticsCalculator calculator = new StatisticsCalculator();

        StatisticsResult result = calculator.compute(List.of());

        assertEquals(0, result.totalRecords);
        assertEquals(0, result.tempCount);
        assertEquals(0, result.humidityCount);
        assertEquals(0, result.pm25Count);
        assertEquals(0, result.pm10Count);
        assertEquals(0, result.uniqueLocations);
        assertTrue(result.recordsPerLocation.isEmpty());
        assertTrue(result.locationCoordinates.isEmpty());
    }

    private static Root record(int locationId, String latitude, String longitude, SensorDataValue... values) {
        Root root = new Root();

        Location location = new Location();
        location.setId(locationId);
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        root.setLocation(location);

        root.setSensordatavalues(new ArrayList<>(Arrays.asList(values)));
        return root;
    }

    private static SensorDataValue value(String type, String rawValue) {
        SensorDataValue value = new SensorDataValue();
        value.setValue_type(type);
        value.setValue(rawValue);
        return value;
    }
}
