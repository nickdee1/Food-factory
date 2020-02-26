package foodchain.states.meat;

import foodchain.products.Product;
import foodchain.states.State;
import foodchain.states.StoredState;

/**
 * Class represents state of product.
 */
public class RawState extends State {

    /**
     * Constructs state of product.
     */
    public RawState() {
        stateName = "Raw";
    }

    /**
     * Change state of product.
     * @param productContext the product to set state on.
     */
    public void prepare(Product productContext) {
        productContext.setState(new StoredState());
    }
}
