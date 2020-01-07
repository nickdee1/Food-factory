package foodchain.channels;

import foodchain.transactions.MoneyTransaction;
import foodchain.transactions.Transaction;
import foodchain.parties.Party;

/**
 * Channel to pay for products.
 */
public class PaymentChannel implements Channel {

    /**
     * Party which receives money.
     */
    private final Party receiver;

    /**
     * Constructs channel for sending money.
     * @param receiver receiver party to send money to
     */
    public PaymentChannel(Party receiver) {
        this.receiver = receiver;
    }

    /**
     * Method for making transaction between two parties.
     * @param transaction - already created money transaction to transmit
     * @return result if transmission was successful, null otherwise
     */
    public MoneyTransaction makeTransmission(Transaction transaction) {
        if ((transaction.getSender().getPartyName()).equalsIgnoreCase("farmer")) {
            System.out.println("Farmer doesn't send money!");
            return null;
        }
        System.out.println("Money transaction is being made to "+transaction.getReceiver().getPartyName());
        MoneyTransaction moneyTransaction = (MoneyTransaction)transaction;
        receiver.receiveMoney(moneyTransaction);
        return moneyTransaction;
    }


}
