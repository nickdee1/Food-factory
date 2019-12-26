package foodchain.states;

import foodchain.products.Product;

public abstract class State {
    protected Product productContext;
    protected String stateName;

    public State() {

    }

    public String getStateName() {
        return stateName;
    }

    public abstract void prepare(Product product);
}
