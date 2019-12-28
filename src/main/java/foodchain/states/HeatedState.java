package foodchain.states;

import foodchain.products.Product;

public class HeatedState extends State {

    public HeatedState() {
        stateName = "Heated";
    }

    /**
     *
     * @param productContext
     */
    public void prepare(Product productContext) {
        productContext.setState(new PackedState());
    }
}
