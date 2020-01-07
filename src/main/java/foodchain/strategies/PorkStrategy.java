package foodchain.strategies;

import foodchain.products.Product;
import static foodchain.parties.Data.*;

public class PorkStrategy extends Strategy {

    public PorkStrategy(Product product) {
        super(product);
    }

    @Override
    public void initStorageParametres() {
        System.out.println("Store pork...");
        product.setStorageParametres(PORK_STORAGE_TIME, PORK_STORAGE_TEMPERATURE,
                PORK_STORAGE_HUMIDITY);
    }

    @Override
    public void initSellerParametres() {
        System.out.println("Sell pork...");
        product.setSellerParametres(PORK_PACKAGING, PORK_SELLING_PLACE);
    }

    @Override
    public void initProcessorParametres() {
        System.out.println("Process pork...");
        product.setProcessorParametres(PORK_PROCESSING_TEMPERATURE, PORK_CHEMICAL_PROCESSING_DEGREE);
    }
    
}
