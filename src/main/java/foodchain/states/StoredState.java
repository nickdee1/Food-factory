package foodchain.states;

import foodchain.products.Product;

/**
 * Class represents state of product
 */
public class StoredState extends State {

    /**
     * Constructs state of product
     */
    public StoredState() {
        stateName = "Stored";
    }

    /**
     * Change state of product
     * @param productContext the product to set state on
     */
    public void prepare(Product productContext) {
        if ((productContext.getName()).equalsIgnoreCase("pork")) {
            productContext.setState(new HeatedState());
        }
        else productContext.setState(new ProcessedState());
    }
}
