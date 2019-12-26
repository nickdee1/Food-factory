package foodchain;

import foodchain.parties.*;

public class Main {
    
    public static void main(String[] args) {
        
        Farmer farmer = new Farmer();
        Storage storage = new Storage();
        Processor processor = new Processor();
        storage.setNext(farmer);
        processor.setNext(storage);
        Distributor distributor = new Distributor();
        distributor.setNext(processor);
        Seller seller = new Seller();
        seller.setNext(distributor);
        Customer customer = new Customer();
        customer.setNext(seller);
        String productName = "Apple";
        Integer moneyForProduct = 20;

        // TEST COMMUNICATION BETWEEN CUSTOMER AND SELLER
        customer.makeRequest(productName);
        customer.makeTransaction(moneyForProduct);
    }
}
