import kafka.model.Root;
import kafka.service.SensorDataClient;
import kafka.statistics.StatisticsCalculator;
import kafka.statistics.StatisticsResult;
import kafka.statistics.ConsoleReporter;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import kafka.export.JsonExporter;
import kafka.export.XmlExporter;
import kafka.export.PdfExporter;

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

        // simple interactive save menu
        System.out.println();
        System.out.println("Save report as: P - PDF, J - JSON, X - XML, N - none");
        System.out.print(">>");
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine().trim().toUpperCase();
        try {
            if ("P".equals(choice)) {
                PdfExporter pe = new PdfExporter();
                pe.save(res, "report.pdf");
                System.out.println("Saved report.pdf");
            } else
            if ("J".equals(choice)) {
                JsonExporter je = new JsonExporter();
                je.save(res, "report.json");
                System.out.println("Saved report.json");
            } else if ("X".equals(choice)) {
                XmlExporter xe = new XmlExporter();
                xe.save(res, "report.xml");
                System.out.println("Saved report.xml");
            } else {
                System.out.println("No file saved.");
            }
        } catch (Exception ex) {
            System.out.println("Error saving report: " + ex.getMessage());
        }
    }
}