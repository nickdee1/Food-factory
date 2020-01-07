package foodchain.transactions;

/**
 * Interface for transactions
 */
public interface Observable {

    /**
     * Notify all observing parties about this transaction.
     */
    void notifyAllParties();
}
