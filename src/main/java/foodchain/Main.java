package foodchain;

import foodchain.parties.*;
import foodchain.products.Milk;
import foodchain.products.Product;
import foodchain.reporters.PartiesReporter;
import foodchain.reporters.ProductReporter;
import foodchain.reporters.SecurityReporter;
import foodchain.states.StoredState;


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
        String productName = "mIlK";
        Integer moneyForProduct = 45;
        
        // SIMULATION OF ONE PARTY HAVING ALREADY EXISTING PRODUCT
        System.out.println("SIMULATION: STORAGE ALREADY HAS MILK");
        Product alreadyExistingMilk = new Milk();
        alreadyExistingMilk.setState(new StoredState());
        storage.addProductToList(alreadyExistingMilk);

        // TEST COMMUNICATION BETWEEN CUSTOMER AND SELLER 
        customer.makeRequest(productName);
        customer.makeTransaction(moneyForProduct);
        // WITH DOUBLE SPENDING
        System.out.println("SIMULATION: CUSTOMER WANTS MILK AGAIN, SELLER WILL "
                + "TRY TO SELL HIM THE SAME MILK - DOUBLE SPENDING");
        customer.makeRequest(productName);
        customer.makeTransaction(moneyForProduct);
        // TRY AGAIN AND SUCCEED
        System.out.println("SIMULATION: AFTER ATTEMPT TO COMMIT DOUBLE SPENDING "+
                "SELLER WILL FINALLY SELL NEW MILK");
        System.out.println("WHOLE FOOD CHAIN WILL WORK");
        customer.makeRequest(productName);
        customer.makeTransaction(moneyForProduct);


        PartiesReporter pr = new PartiesReporter();
        seller.acceptReporter(pr);

        pr.generateForAll(customer);

        SecurityReporter sr = new SecurityReporter();
        ProductReporter pdr = new ProductReporter(seller.getProductsList());
        pdr.generateForAll();
        sr.generateForAll();
    }
}
