package foodchain.transactions;

import foodchain.parties.Farmer;
import foodchain.parties.Storage;
import foodchain.products.Milk;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTransactionTest {

    private ProductTransaction pt;
    private Milk milk;

    @BeforeEach
    void setup() {
        milk = new Milk();
        pt = new ProductTransaction(new Farmer(), new Storage(), milk);
    }

    @Test
    void getProduct() {
        assertEquals(milk, pt.getProduct());
    }
}