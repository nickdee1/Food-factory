package foodchain.parties;

public class Data {
    
    // STORAGE DATA
    static final Integer APPLE_STORAGE_TIME = 1;
    static final Integer APPLE_STORAGE_TEMPERATURE = 15;
    static final Integer APPLE_STORAGE_HUMIDITY = 40;
    
    static final Integer PORK_STORAGE_TIME = 3;
    static final Integer PORK_STORAGE_TEMPERATURE = -5;
    static final Integer PORK_STORAGE_HUMIDITY = 13;
    
    // milk must not be stored
    static final Integer MILK_STORAGE_TIME = 0;
    static final Integer MILK_STORAGE_TEMPERATURE = 0;
    static final Integer MILK_STORAGE_HUMIDITY = 0;
    
    // PROCESSOR DATA
    static final Integer APPLE_PROCESSING_TEMPERATURE = 0;
    static final Integer APPLE_CHEMICAL_PROCESSING_DEGREE = 50;
    
    static final Integer PORK_PROCESSING_TEMPERATURE = 10;
    static final Integer PORK_CHEMICAL_PROCESSING_DEGREE = 27;
    
    static final Integer MILK_PROCESSING_TEMPERATURE = 4;
    static final Integer MILK_CHEMICAL_PROCESSING_DEGREE = 11;
    
    // DISTRIBUTOR DATA
    static final Integer PRODUCTS_DISTRIBUTION_TIME = 3;
    
    // SELLER DATA
    static final String APPLE_PACKAGING = "POLYETHYLENE";
    static final String APPLE_SELLING_PLACE = "FRUIT SECTION";
    
    static final String PORK_PACKAGING = "VACUUM";
    static final String PORK_SELLING_PLACE = "FRIDGE";
    
    static final String MILK_PACKAGING = "TETRAPACK";
    static final String MILK_SELLING_PLACE = "MILK SECTION";
    
}
