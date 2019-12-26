package foodchain;

import foodchain.reporters.ProductReporter;

public abstract class Product {
    
    protected State state;
    protected Integer price;
    protected String name;
    
    public void accept(ProductReporter reporter) {
        
    }
    
    public void setState(State state) {
        this.state = state;
    }
    
    public State getState() {
        return state;
    }
    
    public Integer getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
