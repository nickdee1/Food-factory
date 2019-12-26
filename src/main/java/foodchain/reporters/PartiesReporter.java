package foodchain.reporters;

import foodchain.MoneyTransaction;
import foodchain.Product;
import foodchain.ProductTransaction;
import foodchain.Transaction;
import foodchain.parties.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PartiesReporter implements Visitor {

    public void generateReportForFarmer(Farmer farmer) {
        JSONObject jsonOut = json4party(farmer);
        generateJSON(jsonOut, "farmer");
    }

    public void generateReportForDistributor(Distributor distributor) {
        JSONObject jsonOut = json4party(distributor);
        generateJSON(jsonOut, "distributor");
    }

    public void generateReportForCustomer(Customer customer) {
        JSONObject jsonOut = json4party(customer);
        generateJSON(jsonOut, "customer");
    }

    public void generateReportForProcessor(Processor processor) {
        JSONObject jsonOut = json4party(processor);
        generateJSON(jsonOut, "processor");
    }

    public void generateReportForSeller(Seller seller) {
        JSONObject jsonOut = json4party(seller);
        generateJSON(jsonOut, "seller");
    }

    public void generateReportForStorage(Storage storage) {
        JSONObject jsonOut = json4party(storage);
        generateJSON(jsonOut, "storage");
    }


    public void generateForAll() {

    }


    private JSONObject json4party(AbstractParty party) {
        JSONObject jsonOut = new JSONObject();
        jsonOut.put("name", party.getPartyName());

        JSONArray products = new JSONArray();
        for (Product p : party.getProductsList()) {
            JSONObject prod = new JSONObject();
            prod.put("product", p.getName());
            prod.put("price", p.getPrice());
            prod.put("state", p.getState().getStateName());
            products.add(prod);
        }

        JSONArray transactions = new JSONArray();
        for (Transaction t : party.getOwnTransactionsList()) {
            JSONObject trans = new JSONObject();
            trans.put("receiver", t.getReceiver().getPartyName());
            trans.put("sender", t.getSender().getPartyName());
            trans.put("hash", t.getHashCode());

            if (t.getTransactionFlag().equals("PRODUCT")) {
                Product p = ((ProductTransaction) t).getProduct();
                trans.put("name", p.getName());
            }
            else {
                Integer m = ((MoneyTransaction) t).getMoneyAmount();
                trans.put("money_amount", m);
            }
            transactions.add(trans);
        }


        jsonOut.put("products", products);
        jsonOut.put("transactions", transactions);

        return jsonOut;
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
