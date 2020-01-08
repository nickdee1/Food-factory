package foodchain.strategies;

import foodchain.products.Product;
import static foodchain.parties.Data.*;

/**
 * Strategy to initialize characteristics of Apple.
 */
public class AppleStrategy extends Strategy {

    /**
     * Constructs strategy.
     * @param product the product for strategy.
     */
    public AppleStrategy(Product product) {
        super(product);
    }

    /**
     * Init strategy for storage parametres.
     */
    @Override
    public void initStorageParametres() {
        System.out.println("Store apple...");
        product.setStorageParametres(APPLE_STORAGE_TIME, APPLE_STORAGE_TEMPERATURE,
                APPLE_STORAGE_HUMIDITY);
    }

    /**
     * Init strategy for seller parametres.
     */
    @Override
    public void initSellerParametres() {
        System.out.println("Sell apple...");
        product.setSellerParametres(APPLE_PACKAGING, APPLE_SELLING_PLACE);
    }

    /**
     * Init strategy for processor parametres.
     */
    @Override
    public void initProcessorParametres() {
        System.out.println("Process apple...");
        product.setProcessorParametres(APPLE_PROCESSING_TEMPERATURE, APPLE_CHEMICAL_PROCESSING_DEGREE);
    }
    
}
