package foodchain.reporters;

import foodchain.parties.*;
import org.json.JSONObject;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PartiesReporter implements Visitor {

    private List<AbstractParty> parties;

    public PartiesReporter(List<AbstractParty> parties) {
        this.parties = parties;
    }

    public void generateReportForFarmer(Farmer farmer) {
        Map jsonOut = generateMapForParty(farmer);
        generateJSON(new JSONObject(jsonOut), "farmer");
    }

    public void generateReportForDistributor(Distributor distributor) {
        Map jsonOut = generateMapForParty(distributor);
        generateJSON(new JSONObject(jsonOut), "distributor");
    }

    public void generateReportForCustomer(Customer customer) {
        Map jsonOut = generateMapForParty(customer);
        generateJSON(new JSONObject(jsonOut), "customer");
    }

    public void generateReportForProcessor(Processor processor) {
        Map jsonOut = generateMapForParty(processor);
        generateJSON(new JSONObject(jsonOut), "processor");
    }

    public void generateReportForSeller(Seller seller) {
        Map jsonOut = generateMapForParty(seller);
        generateJSON(new JSONObject(jsonOut), "seller");
    }

    public void generateReportForStorage(Storage storage) {
        Map jsonOut = generateMapForParty(storage);
        generateJSON(new JSONObject(jsonOut), "storage");
    }


    public void generateForAll() {
        String output_file = "parties";
        Map<String, Object> outputMap = new LinkedHashMap<String, Object>();

        List<Map> arrayOfParties = new ArrayList<Map>();
        for (AbstractParty p : parties) {
            Map partyMap = generateMapForParty(p);
            arrayOfParties.add(partyMap);
        }

        outputMap.put("parties", arrayOfParties);
        generateJSON(new JSONObject(outputMap), output_file);
    }


<<<<<<< HEAD
=======
    public void generateReportForParty(AbstractParty party) {
        String name = party.getPartyName().toLowerCase();
        Map output = generateMapForParty(party);
        generateJSON(new JSONObject(output), name);
    }


>>>>>>> d3ea9f8005fe44ba142b28856c1f31b144c56d19
    private Map<String, Object> generateMapForParty(AbstractParty party) {
        Map<String, Object> outputMap = new LinkedHashMap<String, Object>();
        outputMap.put("name", party.getPartyName());

        ProductReporter productReporter = new ProductReporter(party.getProductsList());
        TransactionReporter transactionReporter = new TransactionReporter(party.getOwnTransactionsList());

        List<Map> productMaps = productReporter.generateMapsForAll();
        List<Map> transactionMaps = transactionReporter.generateMapsForAll();

        outputMap.put("products", productMaps);
        outputMap.put("transactions", transactionMaps);

        return outputMap;
    }

    private void generateJSON(JSONObject object, String name) {
        String filepath = "reports/" + name + ".json";

        try {
            FileWriter file = new FileWriter(new File(filepath));
            file.write(object.toString(2));
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
