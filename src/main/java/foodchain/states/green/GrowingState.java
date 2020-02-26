package foodchain.states.green;

import foodchain.products.Product;
import foodchain.states.State;

/**
 * Class represents state of product.
 */
public class GrowingState extends State {

    /**
     * Constructs state of product.
     */
    public GrowingState() {
        stateName = "Growing";
    }

    /**
     * Change state of product.
     * @param productContext the product to set state on.
     */
    public void prepare(Product productContext) {
        productContext.setState(new CollectedState());
    }
}
