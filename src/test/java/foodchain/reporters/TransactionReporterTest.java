package foodchain.reporters;

import foodchain.parties.AbstractParty;
import foodchain.parties.Farmer;
import foodchain.parties.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class TransactionReporterTest {


    AbstractParty farmer, storage;

    @BeforeEach
    void setup() {
        farmer = new Farmer();
        storage = new Storage();
        storage.setNext(farmer);
    }

    @Test
    void generateForAll() {
        storage.makeRequest("milk");
        storage.makeTransaction(45);


        TransactionReporter tr = new TransactionReporter(storage.getTransactionsList());
        tr.generateForAll();

        File file = new File("reports/transactions.json");
        assertNotNull(file);
    }
}