package foodchain.channels;

import foodchain.Transaction;

public interface Channel {
    Transaction makeTransmission(Transaction transaction);
}
