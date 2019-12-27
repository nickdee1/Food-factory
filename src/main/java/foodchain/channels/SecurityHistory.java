package foodchain.channels;

import foodchain.reporters.report.SecurityMessage;

import java.util.ArrayList;
import java.util.List;

public class SecurityHistory {
    private static SecurityHistory instance = null;
    private List<SecurityMessage> messages;

    private SecurityHistory() {
        messages = new ArrayList<SecurityMessage>();
    }

    public static SecurityHistory getInstance() {
        if (instance == null)
            instance = new SecurityHistory();
        return instance;
    }

    public  List<SecurityMessage> getMessages() {
        return messages;
    }

    public void addMessage(SecurityMessage message) {
        messages.add(message);
    }
}
