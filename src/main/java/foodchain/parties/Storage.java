package foodchain.parties;

import foodchain.transactions.MoneyTransaction;
import foodchain.products.Product;
import foodchain.transactions.ProductTransaction;
import foodchain.transactions.Transaction;
import foodchain.reporters.PartiesReporter;
import java.util.ArrayList;

import static foodchain.parties.Data.*;

/**
 * Storage class in simulation.
 */
public class Storage extends AbstractParty {

    /**
     * Construct storage party.
     */
    public Storage() {
        demoTransactionsList = new ArrayList<Transaction>();
        demoOwnTransactionsList = new ArrayList<Transaction>();
        demoProductsList = new ArrayList<Product>();
        moneyReceived = false;
        partyName = "Storage";
    }

    /**
     * Process product as storage.
     * @param product the  product to be processed.
     */
    private void store(Product product) {
        super.prepareProductToNextStage(product);
        System.out.println("Product state in storage is "+product.getState().getStateName());
        initStorageParametres(product);
        System.out.println(product.getName()+" storage parametres: "+product.getStorageParametres().toString());
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

    /**
     * Initialize parametres of product after storing.
     * @param product the product to be processed
     */
    private void initStorageParametres(Product product) {
        if ((product.getName()).equalsIgnoreCase("apple")) {
            System.out.println("Store apple...");
            product.setStorageParametres(APPLE_STORAGE_TIME, APPLE_STORAGE_TEMPERATURE,
                    APPLE_STORAGE_HUMIDITY);
        }
        else if ((product.getName()).equalsIgnoreCase("pork")) {
            System.out.println("Store pork...");
            product.setStorageParametres(PORK_STORAGE_TIME, PORK_STORAGE_TEMPERATURE,
                    PORK_STORAGE_HUMIDITY);
        }
        else if ((product.getName()).equalsIgnoreCase("milk")) {
            System.out.println("Store milk...");
            product.setStorageParametres(MILK_STORAGE_TIME, MILK_STORAGE_TEMPERATURE,
                    MILK_STORAGE_HUMIDITY);
        }
    }

}
