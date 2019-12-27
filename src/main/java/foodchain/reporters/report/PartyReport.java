package foodchain.reporters.report;

import foodchain.products.Product;
import foodchain.transactions.Transaction;

import java.util.List;

public class PartyReport extends Report {

    List<Product> productList;
    List<Transaction> transactionList;

    String generateReport() {
        return null;
    }
}
