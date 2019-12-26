package foodchain.channels;

import foodchain.ProductTransaction;
import foodchain.Transaction;
import foodchain.parties.Party;

public class SellingChannel implements Channel {

    private final Party receiver;

    public SellingChannel(Party receiver) {
   
        this.receiver = receiver;
    }

    public ProductTransaction makeTransmission(Transaction transaction) {
        System.out.println("Product transaction is being made...");
        ProductTransaction productTransaction = (ProductTransaction)transaction;
        receiver.receiveProduct(productTransaction);
        return productTransaction;
    }

}
