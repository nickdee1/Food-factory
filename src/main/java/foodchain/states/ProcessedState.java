package foodchain.states;

import foodchain.products.Product;

public class ProcessedState extends State {

    public ProcessedState() {
       stateName = "Processed";
    }

    /**
     *
     * @param productContext
     */
    public void prepare(Product productContext) {
        productContext.setState(new DeliveredState());
    }
}
