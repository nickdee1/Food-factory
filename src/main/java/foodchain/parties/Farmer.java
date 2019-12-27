package foodchain.parties;

import foodchain.transactions.MoneyTransaction;
import foodchain.transactions.ProductTransaction;
import foodchain.transactions.Transaction;
import foodchain.products.Product;
import foodchain.*;
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

    public Product makeProduct(String productName) {
        Product product = FoodFactory.makeProduct(productName);
        if (product != null) {
            addProduct(product);
        }
        return product;
    }

    @Override
    public void makeTransaction(Integer money) {
        System.out.println("Farmer does not pay for anything!");
    }

    public void acceptReporter(PartiesReporter partiesReporter) {
        partiesReporter.generateReportForParty(this);
    }

    @Override
    public void receiveProduct(ProductTransaction transaction) {
        System.out.println("Farmer does not receive, but produces!");
    }
    
    @Override
    public void receiveMoney(MoneyTransaction transaction) {
        Integer receivedMoney = transaction.getMoneyAmount();
        if (receivedMoney.equals(currentRequestedProduct.getPrice())) {
            balance += receivedMoney;
        }
        super.receiveMoney(transaction);
        makeTransaction(transaction.getSender(), currentRequestedProduct);
    }

    @Override
    public void makeRequest(String productName) {
        System.out.println("Farmer does not make requests!");
    }
    
    @Override
    public void makeTransaction(Party receiver, Product product) {
        product.setIsCurrentlyProcessed(true);
        super.makeTransaction(receiver, product);
    }
 
}
