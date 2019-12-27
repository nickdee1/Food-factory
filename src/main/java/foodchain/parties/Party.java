package foodchain.parties;

import foodchain.transactions.MoneyTransaction;
import foodchain.products.Product;
import foodchain.transactions.ProductTransaction;
import foodchain.transactions.Transaction;
import foodchain.reporters.PartiesReporter;

public interface Party {
    void makeTransaction(Party receiver, Product product);
    void makeTransaction(Integer money);
    void receiveProduct(ProductTransaction transaction);
    void receiveMoney(MoneyTransaction transaction);
    // observer
    void updateTransactions(Transaction transaction);
    // visitor
    void acceptReporter(PartiesReporter partiesReporter);
    // chain of responsibility
    void setNext(Party party); 
    void makeRequest(String productName);
    void getRequest(String productName, Party sender);

    // some public methods
    String getPartyName();
    void removeProduct(Product product);
    void increaseAttempts();
    void setDoubleSpending();
}
