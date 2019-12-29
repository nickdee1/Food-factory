package foodchain.channels;

import foodchain.parties.AbstractParty;
import foodchain.parties.Party;
import foodchain.products.Product;
import foodchain.reporters.report.SecurityMessage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    public List<SecurityMessage> getMessages() {
        return messages;
    }

    public void reportForbiddenAction(Party sender, Party receiver, Product product) {
        SecurityMessage message = new SecurityMessage();
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        message.sender = sender.getPartyName();
        message.receiver = receiver.getPartyName();
        message.product = product;
        message.message = "Attempt to commit transaction with the product that was already sold";
        message.timestamp = dateFormat.format(currentDate);
        messages.add(message);
    }
}