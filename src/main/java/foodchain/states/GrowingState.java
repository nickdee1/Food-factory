package foodchain.states;

import foodchain.products.Product;

public class GrowingState extends State {

    public GrowingState() {
        stateName = "Growing";
    }
    
    /**
     *
     * @param productContext
     */
    public void prepare(Product productContext) {
        productContext.setState(new CollectedState());
    }
}
