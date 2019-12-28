package foodchain.states;

import foodchain.products.Product;

/**
 *
 * @author admin1
 */
public class SoldState extends State {

    public SoldState() {
        stateName = "Sold";
    }

    /**
     * Only prints warning what product is already prepared.
     * @param productContext
     */
    public void prepare(Product productContext) {
        System.out.println("Product is already prepared!\n");  
    }
}