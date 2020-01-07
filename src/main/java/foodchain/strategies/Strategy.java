package foodchain.strategies;

import foodchain.products.Product;

abstract public class Strategy {
    
    protected Product product;

    public Strategy(Product product) {
        this.product = product;
    }
    
    public abstract void initStorageParametres();
    public abstract  void initSellerParametres();
    public abstract  void initProcessorParametres();
}
