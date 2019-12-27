package foodchain.channels;

import foodchain.transactions.MoneyTransaction;
import foodchain.transactions.Transaction;
import foodchain.parties.Party;

public class PaymentChannel implements Channel {

    private final Party receiver;

    public PaymentChannel(Party receiver) {
        this.receiver = receiver;
    }

    public MoneyTransaction makeTransmission(Transaction transaction) {
        if ((transaction.getSender().getPartyName()).equalsIgnoreCase("farmer")) {
            System.out.println("Farmer doesn't send money!");
            return null;
        }
        System.out.println("Money transaction is being made to "+transaction.getReceiver().getPartyName());
        MoneyTransaction moneyTransaction = (MoneyTransaction)transaction;
        receiver.receiveMoney(moneyTransaction);
        return moneyTransaction;
    }


}
