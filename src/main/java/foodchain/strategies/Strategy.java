package foodchain.strategies;

import foodchain.products.Product;

/**
 * Common strategy class that defines execution method providing the
 * strategy implementation.
 */
abstract public class Strategy {

    /**
     * Product to change characteristics of.
     */
    protected Product product;

    /**
     * Constructs strategy.
     * @param product the product for strategy.
     */
    public Strategy(Product product) {
        this.product = product;
    }

    /**
     * Init strategy for storage parametres.
     */
    public abstract void initStorageParametres();

    /**
     * Init strategy for seller parametres.
     */
    public abstract  void initSellerParametres();

    /**
     * Init strategy for processor parametres.
     */
    public abstract  void initProcessorParametres();
}
