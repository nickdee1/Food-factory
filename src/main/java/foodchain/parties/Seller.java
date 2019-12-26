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
        super.prepareProductToNextStage(product);
        System.out.println("Product state in seller is "+product.getState().getStateName());
        // TODO
    }

    @Override
    public void acceptReporter(PartiesReporter partiesReporter) {
        partiesReporter.generateReportForSeller(this);
    }

    @Override
    public void receiveProduct(ProductTransaction transaction) {
        super.receiveProduct(transaction);
        Product product = transaction.getProduct();
        System.out.println("Seller has received "+product.getName());
        sellProduct(product);
        sendProduct(product);
    }

    @Override
    public void receiveMoney(MoneyTransaction transaction) {
        super.receiveMoney(transaction);
        Integer receivedMoney = transaction.getMoneyAmount();
        if (currentRequestedProduct != null &&
                receivedMoney.equals(currentRequestedProduct.getPrice())) {
            makeRequest(currentRequestedProduct.getName());
            makeTransaction(receivedMoney);
        }
    }
    
    private void sendProduct(Product product) {
        if (currentRequestedProduct != null) {
            makeTransaction(currentRequestingParty, product);
            currentRequestedProduct = null;
            currentRequestingParty = null;
        }
    }

}
