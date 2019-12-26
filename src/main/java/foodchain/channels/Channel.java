package foodchain.channels;

import foodchain.transactions.Transaction;

public interface Channel {
    Transaction makeTransmission(Transaction transaction);
}
