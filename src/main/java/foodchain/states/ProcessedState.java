package foodchain.states;

import foodchain.products.Product;

/**
 * Class represents state of product.
 */
public class ProcessedState extends State {

    /**
     * Constructs state of product.
     */
    public ProcessedState() {
       stateName = "Processed";
    }

    /**
     * Change state of product.
     * @param productContext the product to set state on.
     */
    public void prepare(Product productContext) {
        productContext.setState(new DeliveredState());
    }
}
