package foodchain.parties;

import foodchain.transactions.MoneyTransaction;
import foodchain.transactions.ProductTransaction;
import foodchain.transactions.Transaction;
import foodchain.products.Product;
import foodchain.reporters.PartiesReporter;
import java.util.ArrayList;

/**
 * Distributor class in simulation.
 */
public class Farmer extends AbstractParty {

    private Integer balance;

    /**
     * Construct farmer party.
     */
    public Farmer() {
        demoTransactionsList = new ArrayList<Transaction>();
        demoOwnTransactionsList = new ArrayList<Transaction>();
        demoProductsList = new ArrayList<Product>();
        balance = 0;
        moneyReceived = false;
        partyName = "Farmer";
    }

    /**
     * Only prints warning that farmer doesn't pay money.
     * @param money the money being processed.
     */
    @Override
    public void makeTransaction(Integer money) {
        System.out.println("Farmer does not pay for anything!");
    }

    /**
     * Only prints warning that farmer doesn't receive products.
     * @param transaction the transaction to be processed.
     */
    @Override
    public void receiveProduct(ProductTransaction transaction) {
        System.out.println("Farmer does not receive, but produces!");
    }
    
    /**
     * Receives money for product and sends it to the current
     * requesting party.
     * @param transaction to be processed.
     */
    @Override
    public void receiveMoney(MoneyTransaction transaction) {
        Integer receivedMoney = transaction.getMoneyAmount();
        if (receivedMoney.equals(currentRequestedProduct.getPrice())) {
            balance += receivedMoney;
        }
        super.receiveMoney(transaction);
        makeTransaction(transaction.getSender(), currentRequestedProduct);
    }

    /**
     * Only prints warning that farmer doesn't make requests.
     * @param productName name of the product.
     */
    @Override
    public void makeRequest(String productName) {
        System.out.println("Farmer does not make requests!");
    }
 
}
