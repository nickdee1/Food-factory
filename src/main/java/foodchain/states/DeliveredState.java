package foodchain.states;

import foodchain.products.Product;

public class DeliveredState extends State {

    public DeliveredState() {
        stateName = "Delivered";
    }
    
    /**
     *
     * @param productContext
     */
    public void prepare(Product productContext) {
        productContext.setState(new PackedState());
    }
}
