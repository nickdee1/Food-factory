package foodchain.states.meat;

import foodchain.products.Product;
import foodchain.states.PackedState;
import foodchain.states.State;

/**
 * Class represents state of product.
 */
public class HeatedState extends State {

    /**
     * Constructs state of product.
     */
    public HeatedState() {
        stateName = "Heated";
    }

    /**
     * Change state of product.
     * @param productContext the product to set state on.
     */
    public void prepare(Product productContext) {
        productContext.setState(new PackedState());
    }
}
