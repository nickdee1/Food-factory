package foodchain.states;

import foodchain.products.Product;

public class StoredState extends State {

    public StoredState() {
        stateName = "Stored";
    }

    /**
     *
     * @param productContext
     */
    public void prepare(Product productContext) {
        if ((productContext.getName()).equalsIgnoreCase("pork")) {
            productContext.setState(new HeatedState());
        }
        else productContext.setState(new ProcessedState());
    }
}
