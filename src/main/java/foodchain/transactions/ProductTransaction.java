package foodchain.transactions;

import foodchain.products.Product;
import foodchain.parties.Party;
import java.util.Date;

public class ProductTransaction extends Transaction {
    
    private final Product product;

    public ProductTransaction(Party receiver, Party sender, Date timestamp,
            Product product) {
        super(receiver, sender, timestamp);
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public String getTransactionFlag() {
        return "PRODUCT";
    }
}