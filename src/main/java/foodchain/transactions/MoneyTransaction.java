package foodchain.transactions;

import foodchain.parties.Party;

/**
 * Class for money transaction.
 */
public class MoneyTransaction extends Transaction {

    /**
     * Amount of money to be transmitted.
     */
    private final Integer moneyAmount;

    /**
     * Constructs money transaction between parties.
     * @param receiver the party which receives money.
     * @param sender the party which sends money.
     * @param moneyAmount amount of money to be transmitted.
     */
    public MoneyTransaction(Party receiver, Party sender, Integer moneyAmount) {
        super(receiver, sender);
        this.moneyAmount = moneyAmount;
    }

    /**
     * Get money amount.
     * @return amount of money.
     */
    public int getMoneyAmount() {
        return moneyAmount;
    }

    /**
     * MONEY transaction flag to distinguish transaction type.
     * @return transaction flag.
     */
    public TransactionType getTransactionFlag() {
        return TransactionType.MONEY;
    }
}
