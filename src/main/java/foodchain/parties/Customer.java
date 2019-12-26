package foodchain.parties;

import foodchain.MoneyTransaction;
import foodchain.Product;
import foodchain.ProductTransaction;
import foodchain.Transaction;
import foodchain.reporters.PartiesReporter;
import java.util.ArrayList;

import java.util.LinkedList;

public class Customer extends AbstractParty {

    public Customer() {
        transactionsList = new LinkedList<Transaction>();
        ownTransactionsList = new LinkedList<Transaction>();
        productsList = new ArrayList<Product>();
        name = "Customer";
    }
    
    public void buyProduct(Product product) {
        super.prepareProductToNextStage(product);
        System.out.println("Product state in customer is "+product.getState().getStateName());
        System.out.println("CONGRATULATIONS, FOOD CHAIN IS COMPLETED");
    }

    @Override
    public void acceptReporter(PartiesReporter partiesReporter) {
        partiesReporter.generateReportForCustomer(this);
    }

    @Override
    public void receiveProduct(ProductTransaction transaction) {
        super.receiveProduct(transaction);
        Product product = transaction.getProduct();
        System.out.println("Customer has received "+product.getName());
        buyProduct(product);
    }

    @Override
    public void receiveMoney(MoneyTransaction transaction) {
        System.out.println("Customer doesn't receive money, but pays!");
    }
    
    @Override
    public void getRequest(String productName, Party sender) {
        System.out.println("Customer doesn't get any requests!");
    }
    
    @Override
    public void makeTransaction(Party receiver, Product product) {
        System.out.println("Customer doesn't send any products!");
    }

}
