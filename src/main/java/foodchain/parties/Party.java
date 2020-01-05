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

    // all functions have javadocs in classes implementing this interface
    void makeTransaction(Integer money);
    void receiveProduct(ProductTransaction transaction);
    void receiveMoney(MoneyTransaction transaction);
    
    // observer
    void updateTransactions(Transaction transaction);
    
    // visitor
    void acceptReporter(PartiesReporter partiesReporter);
    
    // chain of responsibility
    void setNext(AbstractParty party); 
    void makeRequest(String productName);

    // some public methods
    String getPartyName();
    Party getCurrentRequestingParty();
    AbstractParty getNextParty();
    void removeProduct(Product product);
    void increaseAttempts();
    void setDoubleSpending();
}
