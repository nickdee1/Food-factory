package foodchain.channels;

import foodchain.ProductTransaction;
import foodchain.Transaction;
import foodchain.parties.Party;

public class SellingChannel implements Channel {

    private final Party sender;
    private final Party receiver;

    public SellingChannel(Party sender, Party receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public ProductTransaction makeTransmission(Transaction transaction) {
        System.out.println("Product transaction is being made...");
        ProductTransaction productTransaction = (ProductTransaction)transaction;
        receiver.receiveProduct(productTransaction);
        return productTransaction;
    }

}
