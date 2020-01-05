package foodchain.channels;

import foodchain.parties.Party;
import foodchain.products.Product;
import foodchain.reporters.report.SecurityMessage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class storing reports about forbidden actions
 */
public class SecurityHistory {


    /**
     * Variable storing instance of this class
     */
    private static SecurityHistory instance = null;

    /**
     * List of forbidden reports (messages)
     */
    private List<SecurityMessage> messages;


    /**
     * Constricts empty forbidden reports list
     */
    private SecurityHistory() {
        messages = new ArrayList<SecurityMessage>();
    }

    /**
     * Get static instance of reports history
     * @return static instance of Security History
     */
    public static SecurityHistory getInstance() {
        if (instance == null)
            instance = new SecurityHistory();
        return instance;
    }

    /**
     * Method for creating and saving report about forbidden action committed
     * by certain parties
     * @param sender the party who tried to send product
     * @param receiver the party who tried to receive product
     * @param product the product parties tried to transmit
     */
    void reportForbiddenAction(Party sender, Party receiver, Product product) {
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

    /**
     * Returns all reports about forbidden actions
     * @return lists with messages about with info about forbidden actions
     */
    public List<SecurityMessage> getMessages() {
        return messages;
    }
}