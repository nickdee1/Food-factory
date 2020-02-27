package foodchain.channels;

import foodchain.transactions.MoneyTransaction;
import foodchain.transactions.Transaction;
import foodchain.parties.Party;

/**
 * Channel to pay for products.
 */
public class PaymentChannel implements Channel<MoneyTransaction> {

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
    public MoneyTransaction makeTransmission(MoneyTransaction transaction) {
        if ((transaction.getSender().getPartyName()).equalsIgnoreCase("farmer")) {
            System.err.println("Farmer doesn't send money!");
            return null;
        }
        System.out.println("Money transaction is being made to "+transaction.getReceiver().getPartyName());
        receiver.receiveMoney(transaction);
        return transaction;
    }


}
