package foodchain.channels;

import foodchain.reporters.report.SecurityMessage;
import foodchain.transactions.ProductTransaction;
import foodchain.transactions.Transaction;
import foodchain.parties.Party;
import foodchain.products.Product;
import sun.plugin2.message.Message;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SellingChannel implements Channel {

    private final Party receiver;

    public SellingChannel(Party receiver) {
   
        this.receiver = receiver;
    }

    public ProductTransaction makeTransmission(Transaction transaction) {
        System.out.println("Product transaction is being made...");
        ProductTransaction productTransaction = (ProductTransaction)transaction;
        Product product = productTransaction.getProduct();
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

            // TODO: - Refactor
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            SecurityMessage message = new SecurityMessage();
            message.message = "Attempt to commit double spending";
            message.sender = transaction.getSender().getPartyName();
            message.receiver = transaction.getReceiver().getPartyName();
            message.product = product;
            message.timestamp = dateFormat.format(currentDate);

            SecurityHistory sh = SecurityHistory.getInstance();
            sh.addMessage(message);
            return null;
        }
        receiver.receiveProduct(productTransaction);
        return productTransaction;
    }

}
