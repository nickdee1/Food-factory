package foodchain;

import foodchain.parties.*;

public class Main {
    
    public static void main(String[] args) {
        
        Farmer farmer = new Farmer();
        Storage storage = new Storage();
        Processor processor = new Processor();
        storage.setNext(farmer);
        processor.setNext(storage);
        String productName = "Apple";
        Integer moneyForProduct = 20;

        // TEST COMMUNICATION BETWEEN DISTRIBUTOR AND PROCESSOR
        Distributor distributor = new Distributor();
        distributor.setNext(processor);
        distributor.makeRequest(productName, distributor);
        distributor.makeTransaction(moneyForProduct);
    }
}
