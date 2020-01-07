package foodchain.strategies;

import foodchain.products.Product;
import static foodchain.parties.Data.*;

public class MilkStrategy extends Strategy {

    public MilkStrategy(Product product) {
        super(product);
    }

    @Override
    public void initStorageParametres() {
        System.out.println("Store milk...");
        product.setStorageParametres(MILK_STORAGE_TIME, MILK_STORAGE_TEMPERATURE,
                MILK_STORAGE_HUMIDITY);
    }

    @Override
    public void initSellerParametres() {
        System.out.println("Sell milk...");
        product.setSellerParametres(MILK_PACKAGING, MILK_SELLING_PLACE);
    }

    @Override
    public void initProcessorParametres() {
        System.out.println("Process milk...");
        product.setProcessorParametres(MILK_PROCESSING_TEMPERATURE, MILK_CHEMICAL_PROCESSING_DEGREE);
    }
    
}
