package foodchain.reporters;

import foodchain.MoneyTransaction;
import foodchain.ProductTransaction;
import foodchain.Transaction;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TransactionReporter implements Visitor {


    public void generateForMoneyTransaction(MoneyTransaction transaction) {
        JSONObject jsonOut = new JSONObject();
        jsonOut.put("receiver", transaction.getReceiver().getPartyName());
        jsonOut.put("sender", transaction.getSender().getPartyName());
        jsonOut.put("timestamp", "01/01/1970"); // TODO: - Change it
        jsonOut.put("hashcode", transaction.getHashCode());
        jsonOut.put("price", transaction.getMoneyAmount());

        generateJSON(jsonOut, transaction.getHashCode());
    }

    public void generateForProductTransaction(ProductTransaction transaction) {
        JSONObject jsonOut = new JSONObject();
        jsonOut.put("receiver", transaction.getReceiver().getPartyName());
        jsonOut.put("sender", transaction.getSender().getPartyName());
        jsonOut.put("timestamp", "01/01/1970"); // TODO: - Change it
        jsonOut.put("hashcode", transaction.getHashCode());
        jsonOut.put("product", transaction.getProduct().getName());

        generateJSON(jsonOut, transaction.getHashCode());
    }

    public void generateForAll() {

    }

    private void generateJSON(JSONObject object, String name) {
        String filepath = "reports/" + name + ".json";

        try {
            FileWriter file = new FileWriter(new File(filepath));
            file.write(object.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
