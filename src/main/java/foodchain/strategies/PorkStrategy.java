package foodchain.strategies;

import foodchain.products.Product;
import static foodchain.parties.Data.*;

/**
 * Strategy to initialize characteristics of Pork.
 */
public class PorkStrategy extends Strategy {

    /**
     * Constructs strategy.
     * @param product the product for strategy.
     */
    public PorkStrategy(Product product) {
        super(product);
    }

    /**
     * Init strategy for storage parametres.
     */
    @Override
    public void initStorageParametres() {
        System.out.println("Store pork...");
        product.setStorageParametres(PORK_STORAGE_TIME, PORK_STORAGE_TEMPERATURE,
                PORK_STORAGE_HUMIDITY);
    }

    /**
     * Init strategy for seller parametres.
     */
    @Override
    public void initSellerParametres() {
        System.out.println("Sell pork...");
        product.setSellerParametres(PORK_PACKAGING, PORK_SELLING_PLACE);
    }

    /**
     * Init strategy for processor parametres.
     */
    @Override
    public void initProcessorParametres() {
        System.out.println("Process pork...");
        product.setProcessorParametres(PORK_PROCESSING_TEMPERATURE, PORK_CHEMICAL_PROCESSING_DEGREE);
    }
    
}
