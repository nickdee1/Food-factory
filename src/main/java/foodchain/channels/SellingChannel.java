package foodchain.channels;

import foodchain.transactions.ProductTransaction;
import foodchain.transactions.Transaction;
import foodchain.parties.Party;
import foodchain.products.Product;

public class SellingChannel implements Channel {

    private final Party receiver;

    public SellingChannel(Party receiver) {
   
        this.receiver = receiver;
    }

    public ProductTransaction makeTransmission(Transaction transaction) {
        System.out.println("Product transaction is being made...");
        ProductTransaction productTransaction = (ProductTransaction)transaction;
        Product product = productTransaction.getProduct();
        // check for double spending
        if (product.isIsCurrentlyProcessed()) {
            System.out.println("ATTEMPT TO COMMIT DOUBLE SPENDING");
            System.out.println("--------------------------------------------------");
            Party sender = transaction.getSender();
            sender.setDoubleSpending();
            sender.increaseAttempts();
            // reset
            for (Party p : product.getCurrentlyProcessingParties()) {
                p.removeProduct(product);
            }
            product.clearPartyList();
            return null;
        }
        receiver.receiveProduct(productTransaction);
        return productTransaction;
    }
}
