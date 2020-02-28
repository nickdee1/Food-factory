package foodchain.parties;

import foodchain.transactions.MoneyTransaction;
import foodchain.products.Product;
import foodchain.transactions.ProductTransaction;
import foodchain.transactions.Transaction;
import foodchain.reporters.PartiesReporter;
import java.util.ArrayList;

/**
 * Distributor class in simulation.
 */
public class Distributor extends AbstractParty {

    /**
     * Construct distributor party.
     */
    public Distributor() {
        demoTransactionsList = new ArrayList<>();
        demoOwnTransactionsList = new ArrayList<>();
        demoProductsList = new ArrayList<>();
        partyName = "Distributor";
        partyType = PartyType.DISTRIBUTOR;
    }

    /**
     * Process product as distributor.
     * @param product the  product to be processed.
     */
    private void deliverProduct(Product product) {
        super.prepareProductToNextStage(product);
        System.out.println("Product state in distributor is "+product.getState().getStateName());
        addProduct(product);
    }

    /**
     * Receives product transaction transmitted by selling channel.
     * @param transaction the transaction to process.
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
     * Receives money transaction from payment channel.
     * @param transaction the transaction to process.
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

    /**
     * Sends product to the current requesting party.
     * @param product the product to be processed.
     */
    private void sendProduct(Product product) {
        if (currentRequestedProduct != null) {
            makeTransaction(currentRequestingParty, product);
            currentRequestedProduct = null;
            currentRequestingParty = null;
        }
    }
}
