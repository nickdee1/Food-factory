package foodchain.states;

import foodchain.products.Product;

public class RawState extends State {

    public RawState() {
        stateName = "Raw";
    }

    /**
     *
     * @param productContext
     */
    public void prepare(Product productContext) {
        productContext.setState(new StoredState());
    }
}
