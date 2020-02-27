package foodchain.channels;

import foodchain.security_history.SecurityHistory;
import foodchain.transactions.ProductTransaction;
import foodchain.parties.Party;
import foodchain.products.Product;

/**
 * Channel to sell product.
 */
public class SellingChannel implements Channel<ProductTransaction> {

    /**
     * Party which receives product.
     */
    private final Party receiver;

    /**
     * Constructs a channel for sending product.
     * @param receiver receiver party to send product to.
     */
    public SellingChannel(Party receiver) { this.receiver = receiver; }

    /**
     * Transmits product to receiver, checks if there is double spending.
     * @param transaction - already made product transaction to transmit.
     * @return result if transmission was successful, null otherwise.
     */
    public ProductTransaction makeTransmission(ProductTransaction transaction) {
        System.out.println("Product transaction is being made...");
        Product product = transaction.getProduct();

        /* Check for double spending */
        if (product.isIsCurrentlyProcessed()) {
            System.out.println("ATTEMPT TO COMMIT DOUBLE SPENDING");
            System.out.println("--------------------------------------------------");
            Party sender = transaction.getSender();
            sender.setDoubleSpending();
            sender.increaseAttempts();

            /* Report forbidden action */
            SecurityHistory sh = SecurityHistory.getInstance();
            sh.reportForbiddenAction(sender, receiver, product);

            /* Reset products of each Party by stream */
            product.getCurrentlyProcessingParties().stream()
                    .filter(party -> !party.getPartyName().equals("Customer"))
                    .forEach(party -> party.removeProduct(product));

            product.clearPartyList();
            return null;
        }
        receiver.receiveProduct(transaction);
        return transaction;
    }

}
