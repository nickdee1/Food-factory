package foodchain.states;

import foodchain.products.Product;

public class PackedState extends State {

    public PackedState() {
        stateName = "Packed";
    }

    /**
     * Change product's state to next in its processing, mark product
     * as a sold one and remove it from all parties product lists
     * @param productContext
     */
    public void prepare(Product productContext) {
        productContext.setState(new SoldState());
    }
}
