package foodchain.parties;

import foodchain.transactions.MoneyTransaction;
import foodchain.transactions.ProductTransaction;
import foodchain.transactions.Transaction;
import foodchain.products.Product;
import foodchain.reporters.PartiesReporter;
import java.util.ArrayList;

public class Farmer extends AbstractParty {

    private Integer balance;

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
     * @param money
     */
    @Override
    public void makeTransaction(Integer money) {
        System.out.println("Farmer does not pay for anything!");
    }

    /**
     *
     * @param partiesReporter
     */
    public void acceptReporter(PartiesReporter partiesReporter) {
        partiesReporter.generateReportForParty(this);
    }

    /**
     * Only prints warning that farmer doesn't receive products.
     * @param transaction
     */
    @Override
    public void receiveProduct(ProductTransaction transaction) {
        System.out.println("Farmer does not receive, but produces!");
    }
    
    /**
     * Receives money for product and sends it to the current
     * requesting party.
     * @param transaction
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
     * @param productName
     */
    @Override
    public void makeRequest(String productName) {
        System.out.println("Farmer does not make requests!");
    }
 
}
