package foodchain.reporters;

import foodchain.transactions.MoneyTransaction;
import foodchain.transactions.ProductTransaction;
import foodchain.transactions.Transaction;
import org.json.JSONObject;
import java.util.*;

/**
 * Class for generating JSON report for the products in simulation.
 */
public class TransactionReporter extends Reporter {

    /**
     * List of transactions the report is generated for.
     */
    private final List<Transaction> transactionList;

    /**
     * Constructor for TransactionReporter.
     * @param transactions the list with transactions report is generated for.
     */
    public TransactionReporter(List<Transaction> transactions) {
        this.transactionList = transactions;
    }


    /**
     * Method generates JSON report for all products from transactionList list.
     */
    public void generateForAll() {
        Map<String, List> outputMap = new LinkedHashMap<String, List>();
        String output_file = "transactions";

        List<Map> allTransactionsReport = generateMapsForAll();
        outputMap.put("transactions", allTransactionsReport);

        generateJSON(new JSONObject(outputMap), output_file);
    }


    /**
     *  Method generates List of Maps for the transactions in order to grab all its' attributes
     *  and further converting them into JSON format.
     * @return generated List with necessary attributes.
     */
    List<Map> generateMapsForAll() {
        List<Map> arrayOfTransactions = new ArrayList<Map>();
        for (Transaction t : transactionList) {
            if (t.getTransactionFlag().equals("MONEY")) {
                Map moneyTransaction = generateMapForMoneyTransaction( (MoneyTransaction) t );
                arrayOfTransactions.add(moneyTransaction);
            } else {
                Map productTransaction = generateMapProductTransaction( (ProductTransaction) t);
                arrayOfTransactions.add(productTransaction);
            }
        }

        return arrayOfTransactions;
    }


    /**
     * Generates map out of all money transaction attributes.
     * @param transaction the money transaction to be processed.
     * @return map covering all transaction attributes.
     */
     private Map<String, Object> generateMapForMoneyTransaction(MoneyTransaction transaction) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("receiver", transaction.getReceiver().getPartyName());
        map.put("sender", transaction.getSender().getPartyName());
        map.put("timestamp", transaction.getTimestamp());
        map.put("hashcode", transaction.getHashCode());
        map.put("successful", transaction.isSuccessful());
        map.put("price", transaction.getMoneyAmount());

        return map;
    }


    /**
     * Generates map out of all product transaction attributes.
     * @param transaction the product transaction to be processed.
     * @return map covering all transaction attributes.
     */
    private Map<String, Object> generateMapProductTransaction(ProductTransaction transaction) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("receiver", transaction.getReceiver().getPartyName());
        map.put("sender", transaction.getSender().getPartyName());
        map.put("timestamp", transaction.getTimestamp());
        map.put("hashcode", transaction.getHashCode());
        map.put("successful", transaction.isSuccessful());
        map.put("product", transaction.getProduct().getName());

        return map;
    }

}
