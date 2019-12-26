package foodchain.reporters.report;

import foodchain.Product;
import foodchain.Transaction;

import java.util.Date;
import java.util.List;

public abstract class Report {
    String name;
    Date reportDate;

    abstract String generateReport();
}
