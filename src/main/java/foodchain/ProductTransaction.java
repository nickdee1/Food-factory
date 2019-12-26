package foodchain;

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
       
}
