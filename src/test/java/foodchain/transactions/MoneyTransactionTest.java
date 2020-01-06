package foodchain.transactions;

import foodchain.parties.Farmer;
import foodchain.parties.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTransactionTest {

    private MoneyTransaction mt;

    @BeforeEach
    void setup() {
        mt = new MoneyTransaction(new Farmer(), new Storage(), 50);
    }

    @Test
    void getMoneyAmount() {
        assertEquals(50, mt.getMoneyAmount());
    }
}