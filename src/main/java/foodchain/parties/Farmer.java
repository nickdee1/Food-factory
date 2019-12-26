package foodchain.parties;

import foodchain.transactions.MoneyTransaction;
import foodchain.transactions.ProductTransaction;
import foodchain.transactions.Transaction;
import foodchain.products.Product;
import foodchain.*;
import foodchain.reporters.PartiesReporter;
import java.util.ArrayList;
import java.util.LinkedList;


public class Farmer extends AbstractParty {

    private final FoodFactory factory;
    private Integer balance;

    public Farmer() {
        transactionsList = new LinkedList<Transaction>();
        ownTransactionsList = new LinkedList<Transaction>();
        productsList = new ArrayList<Product>();
        factory = new FoodFactory();
        balance = 0;
        moneyReceived = false;
        partyName = "Farmer";
    }

    public Product makeProduct(String productName) {
        Product product = factory.makeProduct(productName);
        if (product != null) {
            productsList.add(product);
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

 
}
