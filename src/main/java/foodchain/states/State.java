package foodchain.states;

import foodchain.products.Product;

public abstract class State {
    protected String stateName;

    public String getStateName() {
        return stateName;
    }

    public abstract void prepare(Product product);
}
