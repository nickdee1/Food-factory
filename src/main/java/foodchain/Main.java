package foodchain;

import foodchain.parties.*;
import foodchain.reporters.PartiesReporter;
import foodchain.reporters.TransactionReporter;
import foodchain.reporters.report.TransactionReport;

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
        String productName = "Apple";
        Integer moneyForProduct = 20;

        // TEST COMMUNICATION BETWEEN CUSTOMER AND SELLER
        customer.makeRequest(productName);
        customer.makeTransaction(moneyForProduct);


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

    }
}
