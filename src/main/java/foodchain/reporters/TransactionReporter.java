package foodchain.reporters;

import foodchain.transactions.MoneyTransaction;
import foodchain.transactions.ProductTransaction;
import foodchain.transactions.Transaction;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TransactionReporter implements Visitor {

    private final List<Transaction> transactionList;

    public TransactionReporter(List<Transaction> transactions) {
        this.transactionList = transactions;
    }


    public void generateForAll() {
        Map<String, List> outputMap = new LinkedHashMap<String, List>();
        String output_file = "transactions";

        List<Map> allTransactionsReport = generateMapsForAll();
        outputMap.put("transactions", allTransactionsReport);

        generateJSON(new JSONObject(outputMap), output_file);
    }


    protected List<Map> generateMapsForAll() {
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


    protected Map<String, Object> generateMapForMoneyTransaction(MoneyTransaction transaction) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("receiver", transaction.getReceiver().getPartyName());
        map.put("sender", transaction.getSender().getPartyName());
        map.put("timestamp", transaction.getTimestamp());
        map.put("hashcode", transaction.getHashCode());
        map.put("successful", transaction.isSuccessful());
        map.put("price", transaction.getMoneyAmount());

        return map;
    }

    protected Map<String, Object> generateMapProductTransaction(ProductTransaction transaction) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("receiver", transaction.getReceiver().getPartyName());
        map.put("sender", transaction.getSender().getPartyName());
        map.put("timestamp", transaction.getTimestamp());
        map.put("hashcode", transaction.getHashCode());
        map.put("successful", transaction.isSuccessful());
        map.put("product", transaction.getProduct().getName());

        return map;
    }

    private void generateJSON(JSONObject object, String name) {
        String filepath = "reports/" + name + ".json";

        try {
            FileWriter file = new FileWriter(new File(filepath));
            file.write(object.toString(2));
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
