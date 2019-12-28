package foodchain.parties;

import foodchain.transactions.MoneyTransaction;
import foodchain.products.Product;
import foodchain.transactions.ProductTransaction;
import foodchain.transactions.Transaction;
import foodchain.reporters.PartiesReporter;
import java.util.ArrayList;

public class Distributor extends AbstractParty {

    public Distributor() {
        demoTransactionsList = new ArrayList<Transaction>();
        demoOwnTransactionsList = new ArrayList<Transaction>();
        demoProductsList = new ArrayList<Product>();
        partyName = "Distributor";
    }

    // process product as distributor
    private void deliverProduct(Product product) {
        super.prepareProductToNextStage(product);
        System.out.println("Product state in distributor is "+product.getState().getStateName());
        addProduct(product);
    }

    /**
     *
     * @param partiesReporter
     */
    public void acceptReporter(PartiesReporter partiesReporter) {
        partiesReporter.generateReportForParty(this);
    }

    /**
     * Receives product transaction transmitted by selling channel,
     * process product as distributor, sends it to next party in food
     * chain.
     * @param transaction
     */
    @Override
    public void receiveProduct(ProductTransaction transaction) {
        super.receiveProduct(transaction);
        Product product = transaction.getProduct();
        System.out.println("Distributor has received "+product.getName());
        deliverProduct(product);
        sendProduct(product);
    }

    /**
     * Receives money transaction from payment channel, forwards
     * request to the next party in chain of responsibility if necessary.
     * @param transaction
     */
    @Override
    public void receiveMoney(MoneyTransaction transaction) {
        super.receiveMoney(transaction);
        Integer receivedMoney = transaction.getMoneyAmount();
        if (receivedMoney.equals(currentRequestedProduct.getPrice())) {
            if (!currentRequestedProduct.isIsReadyToTransmit()) {
                makeRequest(currentRequestedProduct.getName());
                makeTransaction(receivedMoney);
            }
            else {
                sendProduct(currentRequestedProduct);
            }
        }
    }
    
    // sends product to the current requesting party
    private void sendProduct(Product product) {
        if (currentRequestedProduct != null) {
            makeTransaction(currentRequestingParty, product);
            currentRequestedProduct = null;
            currentRequestingParty = null;
        }
    }
}
