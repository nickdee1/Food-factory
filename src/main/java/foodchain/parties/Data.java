package foodchain.parties;

/**
 * Secondary class which contains static constants for
 * products processing.
 */
public class Data {
    
    /* STORAGE DATA */
    public static final Integer APPLE_STORAGE_TIME = 1;
    public static final Integer APPLE_STORAGE_TEMPERATURE = 15;
    public static final Integer APPLE_STORAGE_HUMIDITY = 40;
    
    public static final Integer PORK_STORAGE_TIME = 3;
    public static final Integer PORK_STORAGE_TEMPERATURE = -5;
    public static final Integer PORK_STORAGE_HUMIDITY = 13;
    
    /* milk must not be stored */
    public static final Integer MILK_STORAGE_TIME = 0;
    public static final Integer MILK_STORAGE_TEMPERATURE = 0;
    public static final Integer MILK_STORAGE_HUMIDITY = 0;
    
    /* PROCESSOR DATA */
    public static final Integer APPLE_PROCESSING_TEMPERATURE = 0;
    public static final Integer APPLE_CHEMICAL_PROCESSING_DEGREE = 50;
    
    public static final Integer PORK_PROCESSING_TEMPERATURE = 10;
    public static final Integer PORK_CHEMICAL_PROCESSING_DEGREE = 27;
    
    public static final Integer MILK_PROCESSING_TEMPERATURE = 4;
    public static final Integer MILK_CHEMICAL_PROCESSING_DEGREE = 11;
    
    /* SELLER DATA */
    public static final String APPLE_PACKAGING = "POLYETHYLENE";
    public static final String APPLE_SELLING_PLACE = "FRUIT SECTION";
    
    public static final String PORK_PACKAGING = "VACUUM";
    public static final String PORK_SELLING_PLACE = "FRIDGE";
    
    public static final String MILK_PACKAGING = "TETRAPACK";
    public static final String MILK_SELLING_PLACE = "MILK SECTION";
    
}
