package csvbuilderfiles;

public class CSVBuilderFactory {
    public static ICSVBuilder createCSVBuilder() {
        return new OpenCsvBuilder();
    }
}

