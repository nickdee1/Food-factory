package foodchain.transactions;

import foodchain.parties.Party;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class Transaction implements Observable {

    private final Party receiver;
    private final Party sender;
    private final String timestamp;
    private final String hashCode;
    private String previousHashCode;
    private boolean successful;
    private final Set<Party> observingParties;
    private Transaction previousTransaction;


    public Transaction(Party receiver, Party sender) {
        this.receiver = receiver;
        this.sender = sender;
        this.timestamp = generateTimestamp();
        this.hashCode = generateHashCode(receiver.getPartyName(), sender.getPartyName());
        observingParties = new HashSet<Party>();
    }
    
    public void addParty(Party party) {
        if (!observingParties.contains(party))
            observingParties.add(party);
    }

    private String generateHashCode(String receiverName, String senderName) {
        String localHashCode = receiverName + senderName + new Random(1000).toString();
        return Integer.toString(localHashCode.hashCode());
    }

    private String generateTimestamp() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return dateFormat.format(currentDate);
    }

    public String getHashCode() {
        return hashCode;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Party getSender() {
        return sender;
    }

    public Party getReceiver() {
        return receiver;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public void notifyAllParties() {
        for (Party p : observingParties) {
            p.updateTransactions(this);
        }
    }

    public void setPreviousTransaction(Transaction previousTransaction) {
        this.previousTransaction = previousTransaction;
        try {
            this.previousHashCode = previousTransaction.getHashCode();
        } catch (NullPointerException e) {
            this.previousHashCode = null;
        }
    }
    
    public abstract String getTransactionFlag();

}
