package kafka.statistics;

import kafka.model.Root;
import kafka.model.SensorDataValue;

import java.util.*;
import java.util.stream.Collectors;

public class StatisticsCalculator {

    public StatisticsResult compute(List<Root> records) {
        StatisticsResult r = new StatisticsResult();
        r.totalRecords = records.size();

        List<Double> temps = new ArrayList<>();
        List<Double> hums = new ArrayList<>();
        List<Double> pm25 = new ArrayList<>();
        List<Double> pm10 = new ArrayList<>();

        Map<Long, Integer> perLocation = new HashMap<>();
        Set<Long> uniqueLoc = new HashSet<>();

        for (Root rec : records) {
            if (rec == null) continue;
            if (rec.location != null) {
                uniqueLoc.add((long) rec.location.getId());
                perLocation.put((long) rec.location.getId(), perLocation.getOrDefault((long) rec.location.getId(), 0) + 1);
            }
            if (rec.getSensordatavalues() == null) continue;
            for (SensorDataValue v : rec.getSensordatavalues()) {
                if (v == null || v.getValue() == null) continue;
                String type = v.getValue_type();
                String val = v.getValue().toString();
                try {
                    double d = Double.parseDouble(val);
                    if ("temperature".equalsIgnoreCase(type)) temps.add(d);
                    else if ("humidity".equalsIgnoreCase(type)) hums.add(d);
                    else if ("P2".equalsIgnoreCase(type)) pm25.add(d);
                    else if ("P1".equalsIgnoreCase(type)) pm10.add(d);
                } catch (NumberFormatException ex) {
                    // ignore
                }
            }
        }

        r.tempCount = temps.size();
        if (!temps.isEmpty()) {
            r.avgTemp = temps.stream().mapToDouble(Double::doubleValue).average().orElse(Double.NaN);
            r.minTemp = temps.stream().mapToDouble(Double::doubleValue).min().orElse(Double.NaN);
            r.maxTemp = temps.stream().mapToDouble(Double::doubleValue).max().orElse(Double.NaN);
            r.tempAbove25 = (int) temps.stream().filter(v -> v > 25.0).count();
        }

        r.humidityCount = hums.size();
        if (!hums.isEmpty()) {
            r.avgHumidity = hums.stream().mapToDouble(Double::doubleValue).average().orElse(Double.NaN);
            r.minHumidity = hums.stream().mapToDouble(Double::doubleValue).min().orElse(Double.NaN);
            r.maxHumidity = hums.stream().mapToDouble(Double::doubleValue).max().orElse(Double.NaN);
        }

        r.pm25Count = pm25.size();
        if (!pm25.isEmpty()) {
            r.avgPm25 = pm25.stream().mapToDouble(Double::doubleValue).average().orElse(Double.NaN);
            r.maxPm25 = pm25.stream().mapToDouble(Double::doubleValue).max().orElse(Double.NaN);
            r.pm25Above35 = (int) pm25.stream().filter(v -> v > 35.0).count();
        }

        r.pm10Count = pm10.size();
        if (!pm10.isEmpty()) {
            r.avgPm10 = pm10.stream().mapToDouble(Double::doubleValue).average().orElse(Double.NaN);
            r.maxPm10 = pm10.stream().mapToDouble(Double::doubleValue).max().orElse(Double.NaN);
            r.pm10Above50 = (int) pm10.stream().filter(v -> v > 50.0).count();
        }

        r.uniqueLocations = uniqueLoc.size();
        r.recordsPerLocation = perLocation;

        return r;
    }
}
