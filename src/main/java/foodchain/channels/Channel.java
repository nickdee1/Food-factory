package foodchain.channels;

import foodchain.transactions.Transaction;

/**
 * Interface for payment and selling channels.
 */
public interface Channel {

    /**
     * interface method for making transactions between parties.
     * @param transaction to transmit.
     * @return result if transmission was successful, null otherwise.
     */
    Transaction makeTransmission(Transaction transaction);
}
