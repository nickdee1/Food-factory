package foodchain.reporters;

import foodchain.parties.*;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class PartiesReporterTest {

    AbstractParty farmer, storage, processor, distributor, seller, customer;

    @BeforeEach
    void setup() {
        farmer = new Farmer();
        storage = new Storage();
        processor = new Processor();
        distributor = new Distributor();
        seller = new Seller();
        customer = new Customer();

        storage.setNext(farmer);
        processor.setNext(storage);
        distributor.setNext(processor);
        seller.setNext(distributor);
        customer.setNext(seller);

        customer.makeRequest("milk");
        customer.makeTransaction(45);
    }

    @Test
    void generateForAll() {
        PartiesReporter pr = new PartiesReporter();
        pr.generateForAll(customer);
        File report = new File("reports/parties.json");
        assertNotNull(report);
    }

    @Test
    void generateForParty() {
        PartiesReporter pr = new PartiesReporter();
        farmer.acceptReporter(pr);
        storage.acceptReporter(pr);
        processor.acceptReporter(pr);
        distributor.acceptReporter(pr);
        seller.acceptReporter(pr);
        customer.acceptReporter(pr);

        File farmerRep = new File("reports/Farmer.json");
        File storageRep = new File("reports/Storage.json");
        File processorRep = new File("reports/Processor.json");
        File distributorRep = new File("reports/Distributor.json");
        File sellerRep = new File("reports/Seller.json");
        File customerRep = new File("reports/Customer.json");

        assertNotNull(farmerRep);
        assertNotNull(storageRep);
        assertNotNull(processorRep);
        assertNotNull(distributorRep);
        assertNotNull(sellerRep);
        assertNotNull(customerRep);
    }
}