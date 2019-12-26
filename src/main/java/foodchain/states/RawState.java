package foodchain.states;

import foodchain.products.Product;

public class RawState extends State {

    public RawState(Product productContext) {
        stateName = "Raw";
    }

    public void prepare(Product productContext) {
        productContext.setState(new StoredState());
    }
}
