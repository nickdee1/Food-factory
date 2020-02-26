package foodchain.channels;

import foodchain.transactions.Transaction;

/**
 * Interface for payment and selling channels.
 */
public interface Channel<T> {

    /**
     * Interface method for making transactions between parties.
     * @param transaction to transmit
     * @return result if transmission was successful, null otherwise
     */
    T makeTransmission(T transaction);
}
