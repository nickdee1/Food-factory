package foodchain.reporters;

import foodchain.parties.*;
import foodchain.products.Milk;
import foodchain.products.Product;
import foodchain.states.StoredState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class SecurityReporterTest {



    @BeforeEach
    void setup() {
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
        String productName = "milk";
        Integer moneyForProduct = 45;

        Product alreadyExistingMilk = new Milk();
        alreadyExistingMilk.setState(new StoredState());
        storage.addProductToList(alreadyExistingMilk);

        customer.makeRequest(productName);
        customer.makeTransaction(moneyForProduct);

        customer.makeRequest(productName);
        customer.makeTransaction(moneyForProduct);
    }

    @Test
    void generateForAll() {
        SecurityReporter sr = new SecurityReporter();
        sr.generateForAll();

        File file = new File("security_messages");
        assertNotNull(file);
    }
}