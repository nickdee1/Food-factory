package foodchain.reporters;
import foodchain.parties.*;
import org.json.JSONObject;
import java.util.*;


/**
 * Class for generating JSON report for the parties in simulation
 */
public class PartiesReporter extends Reporter {


    /**
     *  Constructor for PartiesReporter
     */
    public PartiesReporter() {
    }

    /**
     * Method generates JSON report for parties
     * @param start start party
     */
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
        TransactionReporter transactionReporter = new TransactionReporter(party.getTransactionsList());

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
}
