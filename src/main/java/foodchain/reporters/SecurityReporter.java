package foodchain.reporters;

import foodchain.channels.SecurityHistory;
import foodchain.reporters.report.SecurityMessage;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class SecurityReporter extends Reporter {

    private List<SecurityMessage> messages;

    public SecurityReporter() {
        SecurityHistory sh = SecurityHistory.getInstance();
        messages = sh.getMessages();
    }

    public void generateForAll() {
        Map<String, List> outputMap = new LinkedHashMap<String, List>();
        String output = "security_messages";

        List<Map> listOfAllMessages = generateMapsForAll();
        outputMap.put("security_messages", listOfAllMessages);

        generateJSON(new JSONObject(outputMap), output);
    }

    private List<Map> generateMapsForAll() {
        List<Map> listOfAllMaps = new ArrayList<Map>();

        for (SecurityMessage m : messages) {
            Map messageMap = generateMapForSecurityMessage(m);
            listOfAllMaps.add(messageMap);
        }

        return listOfAllMaps;
    }

    private Map<String, Object> generateMapForSecurityMessage(SecurityMessage message) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
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
