package foodchain.transactions;

import foodchain.parties.Party;

/**
 * Class for money transaction
 */
public class MoneyTransaction extends Transaction {

    /**
     * Amount of money to be transmitted
     */
    private final Integer moneyAmount;

    /**
     * @param receiver
     * @param sender
     * @param moneyAmount
     */
    public MoneyTransaction(Party receiver, Party sender, Integer moneyAmount) {
        super(receiver, sender);
        this.moneyAmount = moneyAmount;
    }

    /**
     * Get money amount
     * @return amount of money
     */
    public int getMoneyAmount() {
        return moneyAmount;
    }

    /**
     * MONEY transaction flag to distinguish transaction type
     * @return transaction flag
     */
    public String getTransactionFlag() {
        return "MONEY";
    }
}
