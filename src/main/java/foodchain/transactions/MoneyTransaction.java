package foodchain.transactions;

import foodchain.parties.Party;

public class MoneyTransaction extends Transaction {
    
    private final Integer moneyAmount;
    
    public MoneyTransaction(Party receiver, Party sender, Integer moneyAmount) {
        super(receiver, sender);
        this.moneyAmount = moneyAmount;
    }

    public int getMoneyAmount() {
        return moneyAmount;
    }

    public String getTransactionFlag() {
        return "MONEY";
    }
}
