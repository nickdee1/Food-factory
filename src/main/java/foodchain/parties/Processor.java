package foodchain.parties;

import foodchain.transactions.MoneyTransaction;
import foodchain.products.Product;
import foodchain.transactions.ProductTransaction;
import foodchain.transactions.Transaction;
import java.util.ArrayList;

/**
 * Processor class in simulation.
 */
public class Processor extends AbstractParty {

    /**
     * Construct farmer party.
     */
    public Processor() {
        demoTransactionsList = new ArrayList<>();
        demoOwnTransactionsList = new ArrayList<>();
        demoProductsList = new ArrayList<>();
        partyName = "Processor";
        partyType = PartyType.PROCESSOR;
    }

    /**
     * Process product as distributor.
     * @param product the  product to be processed.
     */
    private void processProduct(Product product) {
        super.prepareProductToNextStage(product);
        System.out.println("Product state in processor is "+product.getState().getStateName());
        chooseStrategy(product);
        strategy.initProcessorParameters();
        System.out.println(product.getName()+" processor parametres: "+product.getProcessorParametres().toString());
        addProduct(product);
    }

    /**
     * Receives product transaction transmitted by selling channel,
     * process product as processor, sends it to next party in food
     * chain.
     * @param transaction the transaction to be processed.
     */
    @Override
    public void receiveProduct(ProductTransaction transaction) {
        super.receiveProduct(transaction);
        Product product = transaction.getProduct();
        System.out.println("Processor has received "+product.getName());
        processProduct(product);
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
