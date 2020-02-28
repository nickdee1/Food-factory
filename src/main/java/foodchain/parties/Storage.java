package foodchain.parties;

import foodchain.transactions.MoneyTransaction;
import foodchain.products.Product;
import foodchain.transactions.ProductTransaction;

import java.util.ArrayList;

/**
 * Storage class in simulation.
 */
public class Storage extends AbstractParty {

    /**
     * Construct storage party.
     */
    public Storage() {
        demoTransactionsList = new ArrayList<>();
        demoOwnTransactionsList = new ArrayList<>();
        demoProductsList = new ArrayList<>();
        moneyReceived = false;
        partyName = "Storage";
        partyType = PartyType.STORAGE;
    }

    /**
     * Process product as storage.
     * @param product the  product to be processed.
     */
    private void store(Product product) {
        super.prepareProductToNextStage(product);
        System.out.println("Product state in storage is "+product.getState().getStateName());
        chooseStrategy(product);
        strategy.initStorageParameters();
        System.out.println(product.getName()+" storage parameters: "+product.getStorageParametres().toString());
        addProduct(product);
    }

    /**
     * Receives product transaction transmitted by selling channel,
     * process product as storage, sends it to next party in food
     * chain.
     * @param transaction the transaction to be processed.
     */
    @Override
    public void receiveProduct(ProductTransaction transaction) {
        super.receiveProduct(transaction);
        Product product = transaction.getProduct();
        System.out.println("Storage has received "+product.getName());
        store(product);
        sendProduct(product);
    }
    
    /**
     * Receives money transaction from payment channel, forwards
     * request to the next party in chain of responsibility if necessary.
     * @param transaction the transaction to be processed.
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
            else sendProduct(currentRequestedProduct);
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
