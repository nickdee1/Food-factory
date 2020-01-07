package foodchain.strategies;

import foodchain.products.Product;
import static foodchain.parties.Data.*;

public class AppleStrategy extends Strategy {

    public AppleStrategy(Product product) {
        super(product);
    }

    @Override
    public void initStorageParametres() {
        System.out.println("Store apple...");
        product.setStorageParametres(APPLE_STORAGE_TIME, APPLE_STORAGE_TEMPERATURE,
                APPLE_STORAGE_HUMIDITY);
    }

    @Override
    public void initSellerParametres() {
        System.out.println("Sell apple...");
        product.setSellerParametres(APPLE_PACKAGING, APPLE_SELLING_PLACE);
    }

    @Override
    public void initProcessorParametres() {
        System.out.println("Process apple...");
        product.setProcessorParametres(APPLE_PROCESSING_TEMPERATURE, APPLE_CHEMICAL_PROCESSING_DEGREE);
    }
    
}
