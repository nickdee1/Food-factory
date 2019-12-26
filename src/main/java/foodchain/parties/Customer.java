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
    }
    
    public void buyProduct(Product product) {

    }

    @Override
    public void acceptReporter(PartiesReporter partiesReporter) {
        partiesReporter.generateReportForCustomer(this);
    }


    public void makeTransaction(Integer money) {

    }

    public void receiveProduct(ProductTransaction transaction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void receiveMoney(MoneyTransaction transaction) {
        System.out.println("Customer doesn't receive money, but pays!");
    }

    public void getRequest(String productName, Party sender) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
