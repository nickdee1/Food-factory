package foodchain.parties;

import foodchain.transactions.MoneyTransaction;
import foodchain.products.Product;
import foodchain.transactions.ProductTransaction;
import foodchain.transactions.Transaction;

import java.util.ArrayList;

/**
 * Seller class in simulation.
 */
public class Seller extends AbstractParty {

    /**
     * Construct seller party.
     */
    public Seller() {
        demoTransactionsList = new ArrayList<Transaction>();
        demoOwnTransactionsList = new ArrayList<Transaction>();
        demoProductsList = new ArrayList<Product>();
        partyName = "Seller";
    }

    /**
     * Process product as distributor.
     * @param product the  product to be processed.
     */
    private void sellProduct(Product product) {
        super.prepareProductToNextStage(product);
        System.out.println("Product state in seller is "+product.getState().getStateName());
        chooseStrategy(product);
        strategy.initSellerParameters();
        System.out.println(product.getName()+" seller parametres: "+product.getSellerParametres().toString());
        addProduct(product);
    }

    /**
     * Receives product transaction transmitted by selling channel,
     * process product as seller, sends it to next party in food
     * chain.
     * @param transaction the transaction to be processed.
     */
    @Override
    public void receiveProduct(ProductTransaction transaction) {
        super.receiveProduct(transaction);
        Product product = transaction.getProduct();
        System.out.println("Seller has received "+product.getName());
        sellProduct(product);
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
