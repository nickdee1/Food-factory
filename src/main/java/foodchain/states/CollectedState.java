package foodchain.states;

import foodchain.products.Product;

/**
 * Class represents state of product
 */
public class CollectedState extends State {

    /**
     * Constructs state of product
     */
    public CollectedState() {
        stateName = "Collected";
    }

    /**
     * Change state of product
     * @param productContext the product to set state on
     */
    public void prepare(Product productContext) {
        productContext.setState(new StoredState());
    }
}
