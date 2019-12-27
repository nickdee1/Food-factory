package foodchain.transactions;

import foodchain.parties.Party;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public abstract class Transaction implements Observable {

    private final Party receiver;
    private final Party sender;
    private final Date timestamp;
    private String hashCode;
    private boolean successful;
    private final Set<Party> observingParties;
    private Transaction previousTransaction;


    public Transaction(Party receiver, Party sender, Date timestamp) {
        this.receiver = receiver;
        this.sender = sender;
        this.timestamp = timestamp;
        generateHashCode("ReceiverNameTemp"); // TODO: - change it
        observingParties = new HashSet<Party>();
    }
    
    public void addParty(Party party) {
        observingParties.add(party);
    }

    private void generateHashCode(String receiverName) {
        hashCode = "228";
    }

    public String getHashCode() {
        return hashCode;
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
    }
    
    public abstract String getTransactionFlag();

}
