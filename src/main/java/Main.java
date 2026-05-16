import kafka.model.Root;
import kafka.service.SensorDataClient;
import kafka.statistics.StatisticsCalculator;
import kafka.statistics.StatisticsResult;
import kafka.statistics.ConsoleReporter;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        SensorDataClient client = new SensorDataClient();
        List<Root> data;
        String url = "https://data.sensor.community/airrohr/v1/filter/box=52.36734243199027,20.819494415027485,52.09692843752652,21.319390572461643";
        System.out.println("Loading data from API: " + url);
        data = client.readFromUrl(url);
        System.out.println("Loaded data from API (records=" + data.size() + ")");

        StatisticsCalculator calc = new StatisticsCalculator();
        StatisticsResult res = calc.compute(data);

        ConsoleReporter reporter = new ConsoleReporter();
        reporter.print(res);
    }
}