package foodchain.reporters;

import foodchain.channels.SecurityHistory;
import foodchain.reporters.report.SecurityMessage;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for generating JSON report for the report messages in simulation.
 */
public class SecurityReporter extends Reporter {

    /**
     * List of messages the report is generated for.
     */
    private List<SecurityMessage> messages;


    /**
     * Constructor for SecurityReporter.
     */
    public SecurityReporter() {
        SecurityHistory sh = SecurityHistory.getInstance();
        messages = sh.getMessages();
    }

    /**
     * Generates report for all report messages.
     */
    public void generateForAll() {
        Map<String, List> outputMap = new LinkedHashMap<>();
        String output = "security_messages";

        List<Map> listOfAllMessages = generateMapsForAll();
        outputMap.put("security_messages", listOfAllMessages);

        generateJSON(new JSONObject(outputMap), output);
    }

    /**
     * Generates list of maps of all messages.
     * @return list with maps of all messages.
     */
    private List<Map> generateMapsForAll() {
        List<Map> listOfAllMaps = new ArrayList<>();

        for (SecurityMessage m : messages) {
            Map messageMap = generateMapForSecurityMessage(m);
            listOfAllMaps.add(messageMap);
        }

        return listOfAllMaps;
    }

    /**
     * Generates map for the report message.
     * @param message the massage to be processed.
     * @return the map covering all message attributes.
     */
    private Map<String, Object> generateMapForSecurityMessage(SecurityMessage message) {
        Map<String, Object> map = new LinkedHashMap<>();
        ProductReporter productReporter = new ProductReporter();

        Map productMap = productReporter.generateMapReportForProduct(message.product);
        map.put("message", message.message);
        map.put("sender", message.sender);
        map.put("receiver", message.receiver);
        map.put("product", productMap);
        map.put("timestamp", message.timestamp);

        return map;
    }
}
