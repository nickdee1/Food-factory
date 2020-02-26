package foodchain.parties;

import foodchain.transactions.MoneyTransaction;
import foodchain.products.Product;
import foodchain.transactions.ProductTransaction;
import foodchain.transactions.Transaction;
import foodchain.reporters.PartiesReporter;

/**
 * Interface for parties taking part in food chain.
 */
public interface Party {

    /**
     * Send money to party which is currently requested to make
     * product by payment channel.
     * @param money value to make transaction.
     */
    void makeTransaction(Integer money);

    /**
     * Receives product transaction transmitted by selling channel.
     * @param transaction the transaction to process.
     */
    void receiveProduct(ProductTransaction transaction);

    /**
     * Receives money transaction from payment channel.
     * @param transaction the transaction to process.
     */
    void receiveMoney(MoneyTransaction transaction);

    /**
     * Adds recent transaction made somewhere in food chain
     * to the list of transactions of whole food chain of
     * current party.
     * @param transaction the transaction to add to the list.
     */
    void updateTransactions(Transaction transaction);

    /**
     * Method for creating party report.
     * @param partiesReporter the reporter to create party report.
     */
    void acceptReporter(PartiesReporter partiesReporter);

    /**
     * Set next party in chain of responsibility.
     * @param next - next party in chain.
     */
    void setNext(AbstractParty next);

    /**
     * Ask next party in chain of responsibility for product
     * defined by its name.
     * @param productName name of product to make request for.
     */
    void makeRequest(String productName);

    /**
     * Get the name of party.
     * @return name of party.
     */
    String getPartyName();

    /**
     * Get the requesting party.
     * @return the requesting party.
     */
    Party getCurrentRequestingParty();

    /**
     * Get next party in chain of responsibility.
     * @return next party in chain.
     */
    AbstractParty getNextParty();

    /**
     * Removes product from list of party's products.
     * @param product to process.
     */
    void removeProduct(Product product);

    /**
     * Increases number of attempts to commit double-spending.
     */
    void increaseAttempts();

    /**
     * Set boolean flag representing attempt to double spending to 'true'.
     */
    void setDoubleSpending();
}
