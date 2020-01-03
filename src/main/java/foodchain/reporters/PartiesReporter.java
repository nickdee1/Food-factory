package foodchain.reporters;

import foodchain.parties.PartiesIterator;
import foodchain.parties.*;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


/**
 * Class for generating JSON report for the parties in simulation
 */
public class PartiesReporter implements Visitor {

    /**
     * List of parties the report is generated for
     */
    private List<AbstractParty> parties;

    public PartiesReporter() {
    }

    /**
     * Constructor for PartiesReporter
     * @param parties the list with parties report is generated for
     */
    public PartiesReporter(List<AbstractParty> parties) {
        this.parties = parties;
    }

    public void generateForAll(AbstractParty start) {
        String output_file = "parties";
        Map<String, Object> outputMap = new LinkedHashMap<String, Object>();

        List<Map> arrayOfParties = new ArrayList<Map>();
        PartiesIterator it = start.iterator();

        arrayOfParties.add(generateMapForParty(start));
        while(it.hasNext()) {
            Map partyMap = generateMapForParty(it.next());
            arrayOfParties.add(partyMap);
        }
        outputMap.put("parties", arrayOfParties);
        generateJSON(new JSONObject(outputMap), output_file);
    }
    /**
     * Method generates JSON report for all parties from parties list {@link #parties}
     */
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

    /**
     * Method generates JSON report for exact party
     * @param party the party report is generated for
     */
    public void generateReportForParty(AbstractParty party) {
        String name = party.getPartyName();
        Map outputMap = generateMapForParty(party);

        generateJSON(new JSONObject(outputMap), name);
    }


    /**
     * Method generates Map for the party in order to grab all it's attributes
     * and further converting them into JSON format
     * @param party the party the Map is generated for
     * @return generated Map with necessary attributes
     */
    private Map<String, Object> generateMapForParty(AbstractParty party) {
        Map<String, Object> outputMap = new LinkedHashMap<String, Object>();
        outputMap.put("name", party.getPartyName());

        ProductReporter productReporter = new ProductReporter(party.getProductsList());
        TransactionReporter transactionReporter = new TransactionReporter(party.getOwnTransactionsList());

        List<Map> productMaps;
        List<Map> transactionMaps;

        try {
            productMaps = productReporter.generateMapsForAll();
        } catch (NullPointerException e) {
            productMaps = new ArrayList<Map>();
        }

        try {
            transactionMaps = transactionReporter.generateMapsForAll();
        } catch (NullPointerException e) {
            transactionMaps = new ArrayList<Map>();
        }


        outputMap.put("products", productMaps);
        outputMap.put("transactions", transactionMaps);

        return outputMap;
    }

    /**
     * Method generates JSON file for the report and saves it into
     * directory /reports/
     * @param object the JSONObject for which the report is generated for
     * @param name the filename without extension report would be saved with
     */
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
