package foodchain.parties;

import foodchain.MoneyTransaction;
import foodchain.Product;
import foodchain.ProductTransaction;
import foodchain.Transaction;
import foodchain.reporters.PartiesReporter;
import java.util.ArrayList;
import java.util.LinkedList;


public class Seller extends AbstractParty {

    public Seller() {
        transactionsList = new LinkedList<Transaction>();
        ownTransactionsList = new LinkedList<Transaction>();
        productsList = new ArrayList<Product>();
    }

    public void sellProduct(Product product) {

    }

    @Override
    public void acceptReporter(PartiesReporter partiesReporter) {
        partiesReporter.generateReportForSeller(this);
    }

    public void makeTransaction(Integer money) {
    }


    public void receiveProduct(ProductTransaction transaction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void receiveMoney(MoneyTransaction transaction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public void makeTransaction(Party receiver, String productName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void getRequest(String productName, Party sender) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
