package foodchain;

import foodchain.parties.*;
import foodchain.products.Milk;
import foodchain.products.Product;
import foodchain.reporters.PartiesReporter;
import foodchain.reporters.SecurityReporter;
import foodchain.states.StoredState;
import foodchain.transactions.Transaction;
import java.util.ArrayList;
import java.util.List;


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
        Product alreadyExistingMilk = new Milk();
        alreadyExistingMilk.setState(new StoredState());
        storage.addProductToList(alreadyExistingMilk);

        // TEST COMMUNICATION BETWEEN CUSTOMER AND SELLER 
        customer.makeRequest(productName);
        customer.makeTransaction(moneyForProduct);
        // WITH DOUBLE SPENDING
        customer.makeRequest(productName);
        customer.makeTransaction(moneyForProduct);
        // TRY AGAIN AND SUCCEED
        customer.makeRequest(productName);
        customer.makeTransaction(moneyForProduct);


        // TEST REPORTS
        List<AbstractParty> parties = new ArrayList<AbstractParty>();
        List<Transaction> tr = new ArrayList<Transaction>();
        parties.add(customer);
        parties.add(seller);
        parties.add(distributor);
        parties.add(processor);
        parties.add(storage);
        parties.add(farmer);

        PartiesReporter pr = new PartiesReporter(parties);
        pr.generateForAll();

        SecurityReporter sr = new SecurityReporter();
        sr.generateForAll();

    }
}
