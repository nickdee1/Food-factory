package foodchain.transactions;

import foodchain.products.Product;
import foodchain.parties.Party;

/**
 * Class for money transaction.
 */
public class ProductTransaction extends Transaction {

    /**
     * Product to be transmitted.
     */
    private final Product product;

    /**
     * Constructs product transaction between parties.
     * @param receiver the party which receives product.
     * @param sender the party which sends product.
     * @param product product to be transmitted.
     */
    public ProductTransaction(Party receiver, Party sender, Product product) {
        super(receiver, sender);
        this.product = product;
    }

    /**
     * Get product.
     * @return product being transmitted.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * PRODUCT transaction flag to distinguish transaction type.
     * @return transaction flag.
     */
    public TransactionType getTransactionFlag() {
        return TransactionType.PRODUCT;
    }
}
