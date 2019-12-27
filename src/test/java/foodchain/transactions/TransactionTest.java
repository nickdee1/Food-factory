package foodchain.transactions;

import foodchain.parties.Farmer;
import foodchain.parties.Storage;
//import org.junit.jupiter.api.Test;

class TransactionTest {


    //@Test
    void getHashCode() {
        Transaction tr = new MoneyTransaction(new Storage(), new Farmer(), 500);
        Transaction newTr = new MoneyTransaction(new Storage(), new Farmer(), 500);

        System.out.println(tr.getHashCode());
        System.out.println(newTr.getHashCode());

        System.out.println(newTr.getTimestamp());
    }
}