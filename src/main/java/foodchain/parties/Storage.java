package foodchain.parties;

import foodchain.transactions.MoneyTransaction;
import foodchain.products.Product;
import foodchain.transactions.ProductTransaction;
import foodchain.transactions.Transaction;
import foodchain.reporters.PartiesReporter;
import java.util.LinkedList;
import java.util.ArrayList;

import static foodchain.parties.Data.*;

public class Storage extends AbstractParty {


    public Storage() {
        transactionsList = new LinkedList<Transaction>();
        ownTransactionsList = new LinkedList<Transaction>();
        productsList = new ArrayList<Product>();
        moneyReceived = false;
        partyName = "Storage";
    }

    public void store(Product product) {
        super.prepareProductToNextStage(product);
        System.out.println("Product state in storage is "+product.getState().getStateName());
        initStorageParametres(product);
        System.out.println(product.getName()+" storage parametres: "+product.getStorageParametres().toString());
        productsList.add(product);
    }

    public void acceptReporter(PartiesReporter partiesReporter) {
        partiesReporter.generateReportForStorage(this);
    }

    @Override
    public void receiveProduct(ProductTransaction transaction) {
        super.receiveProduct(transaction);
        Product product = transaction.getProduct();
        System.out.println("Storage has received "+product.getName());
        store(product);
        sendProduct(product);
    }
    
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
    
    private void sendProduct(Product product) {
        if (currentRequestedProduct != null) {
            makeTransaction(currentRequestingParty, product);
            currentRequestedProduct = null;
            currentRequestingParty = null;
        }
    }

    private void initStorageParametres(Product product) {
        if ((product.getName()).equalsIgnoreCase("apple")) {
            System.out.println("Apple storage parametres: "+product.getStorageParametres().toString());
            System.out.println("Store apple...");
            product.setStorageParametres(APPLE_STORAGE_TIME, APPLE_STORAGE_TEMPERATURE,
                    APPLE_STORAGE_HUMIDITY);
        }
        else if ((product.getName()).equalsIgnoreCase("pork")) {
            System.out.println("Pork storage parametres: "+product.getStorageParametres().toString());
            System.out.println("Store pork...");
            product.setStorageParametres(PORK_STORAGE_TIME, PORK_STORAGE_TEMPERATURE,
                    PORK_STORAGE_HUMIDITY);
        }
        else if ((product.getName()).equalsIgnoreCase("milk")) {
            System.out.println("Milk storage parametres: "+product.getStorageParametres().toString());
            System.out.println("Store milk...");
            product.setStorageParametres(MILK_STORAGE_TIME, MILK_STORAGE_TEMPERATURE,
                    MILK_STORAGE_HUMIDITY);
        }
    }

}
