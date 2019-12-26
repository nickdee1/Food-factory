package foodchain.parties;

import foodchain.MoneyTransaction;
import foodchain.Product;
import foodchain.ProductTransaction;
import foodchain.Transaction;
import foodchain.channels.PaymentChannel;
import foodchain.reporters.PartiesReporter;
import java.util.ArrayList;
import java.util.LinkedList;


public class Distributor extends AbstractParty {

    public Distributor() {
        transactionsList = new LinkedList<Transaction>();
        ownTransactionsList = new LinkedList<Transaction>();
        productsList = new ArrayList<Product>();
    }

    public void deliverProduct(Product product) {
        super.prepareProductToNextStage(product);
        System.out.println("Product state in distributor is "+product.getState().getStateName());
    }

    public void acceptReporter(PartiesReporter partiesReporter) {
        partiesReporter.generateReportForDistributor(this);
    }

    public void makeTransaction(Integer money) {
        Transaction transaction = new MoneyTransaction(nextParty, this, null, money);
        PaymentChannel channel = new PaymentChannel(this, nextParty);
        transaction = channel.makeTransmission(transaction);
        if (!transaction.isSuccessful()) {
            System.out.println("I did something wrong!");
        }
        ownTransactionsList.add(transaction);
    }

    public void receiveProduct(ProductTransaction transaction) {
        transaction.setSuccessful(true);
        ownTransactionsList.add(transaction);
        Product product = transaction.getProduct();
        System.out.println("Distributor has received "+product.getName());
        transaction.addParty(transaction.getSender());
        transaction.addParty(this);
        transaction.notifyAllParties();
        deliverProduct(product);
    }

    public void makeTransaction(Party receiver, String productName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void receiveMoney(MoneyTransaction transaction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void getRequest(String productName, Party sender) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
