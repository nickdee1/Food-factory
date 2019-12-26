package foodchain.channels;

import foodchain.MoneyTransaction;
import foodchain.Transaction;
import foodchain.parties.Party;

public class PaymentChannel implements Channel {

    private final Party sender;
    private final Party receiver;

    public PaymentChannel(Party sender, Party receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public MoneyTransaction makeTransmission(Transaction transaction) {
        System.out.println("Money transaction is being made to "+transaction.getReceiver().getClass());
        MoneyTransaction moneyTransaction = (MoneyTransaction)transaction;
        receiver.receiveMoney(moneyTransaction);
        return moneyTransaction;
    }


}
