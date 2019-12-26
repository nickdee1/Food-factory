package foodchain.parties;

import foodchain.transactions.MoneyTransaction;
import foodchain.products.Product;
import foodchain.transactions.ProductTransaction;
import foodchain.transactions.Transaction;
import foodchain.reporters.PartiesReporter;
import java.util.ArrayList;
import java.util.LinkedList;

import static foodchain.parties.Data.*;


public class Distributor extends AbstractParty {

    public Distributor() {
        transactionsList = new LinkedList<Transaction>();
        ownTransactionsList = new LinkedList<Transaction>();
        productsList = new ArrayList<Product>();
        partyName = "Distributor";
    }

    public void deliverProduct(Product product) {
        super.prepareProductToNextStage(product);
        System.out.println("Product state in distributor is "+product.getState().getStateName());
        // TODO
        productsList.add(product);
    }

    public void acceptReporter(PartiesReporter partiesReporter) {
        partiesReporter.generateReportForParty(this);
    }

    @Override
    public void receiveProduct(ProductTransaction transaction) {
        super.receiveProduct(transaction);
        Product product = transaction.getProduct();
        System.out.println("Distributor has received "+product.getName());
        deliverProduct(product);
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

    // TO USE IN SIMULATION
    public boolean increaseCurrentDistributionTime(Product product) {
        if (product.getDistributionTime() == PRODUCTS_DISTRIBUTION_TIME) {
            return false;
        }
        else {
            product.increaseDistributionTime();
            return true;
        }
    }

}
