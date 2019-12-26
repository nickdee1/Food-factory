package foodchain.transactions;

import foodchain.parties.Party;
import java.util.Date;

public class MoneyTransaction extends Transaction {
    
    private final Integer moneyAmount;
    
    public MoneyTransaction(Party receiver, Party sender, Date timestamp,
            Integer moneyAmount) {
        super(receiver, sender, timestamp);
        this.moneyAmount = moneyAmount;
    }

    public int getMoneyAmount() {
        return moneyAmount;
    }


    public String getTransactionFlag() {
        return "MONEY";
    }
}
