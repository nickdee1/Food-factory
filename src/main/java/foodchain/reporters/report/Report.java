package foodchain.reporters.report;

import java.util.Date;

public abstract class Report {
    String name;
    Date reportDate;

    abstract String generateReport();
}
