package foodchain.parties;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractPartyTest {

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
    }


    @Test
    void receiveProduct() {
        customer.makeRequest("milk");
        customer.makeTransaction(45);

        assertNotNull(customer.getProductsList());
        assertEquals(45, customer.getProductsList().get(0).getPrice());
        assertEquals("Milk", customer.getProductsList().get(0).getName());
    }

}