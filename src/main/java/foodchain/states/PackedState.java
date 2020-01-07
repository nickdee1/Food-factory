package foodchain.states;

import foodchain.products.Product;

/**
 * Class represents state of product.
 */
public class PackedState extends State {

    /**
     * Constructs state of product.
     */
    public PackedState() {
        stateName = "Packed";
    }

    /**
     * Change state of product.
     * @param productContext the product to set state on.
     */
    public void prepare(Product productContext) {
        productContext.setState(new SoldState());
    }
}
