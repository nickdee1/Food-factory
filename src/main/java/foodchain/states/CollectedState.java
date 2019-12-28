package foodchain.states;

import foodchain.products.Product;

public class CollectedState extends State {

    public CollectedState() {
        stateName = "Collected";
    }

    /**
     *
     * @param productContext
     */
    public void prepare(Product productContext) {
        productContext.setState(new StoredState());
    }
}
