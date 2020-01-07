package foodchain.states;

import foodchain.products.Product;

/**
 * Class represents state of product.
 */
public class AliveState extends State {

    /**
     * Constructs state of product.
     */
    public AliveState() {
        stateName = "Alive";
    }

    /**
     * Change state of product.
     * @param productContext the product to set state on.
     */
    public void prepare(Product productContext) {
        productContext.setState(new RawState());
    }
}
