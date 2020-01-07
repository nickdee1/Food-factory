package foodchain.states;

import foodchain.products.Product;

/**
 * Class represents state of product.
 */
public class SoldState extends State {

    /**
     * Constructs state of product.
     */
    public SoldState() {
        stateName = "Sold";
    }

    /**
     * Only prints warning what product is already prepared.
     * @param productContext the product to set state on.
     */
    public void prepare(Product productContext) {
        System.out.println("Product is already prepared!\n");  
    }
}