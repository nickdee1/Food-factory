package foodchain.parties;

import foodchain.transactions.MoneyTransaction;
import foodchain.products.Product;
import foodchain.transactions.ProductTransaction;
import foodchain.transactions.Transaction;
import foodchain.reporters.PartiesReporter;
import java.util.ArrayList;

import static foodchain.parties.Data.*;

public class Processor extends AbstractParty {

    public Processor() {
        demoTransactionsList = new ArrayList<Transaction>();
        demoOwnTransactionsList = new ArrayList<Transaction>();
        demoProductsList = new ArrayList<Product>();
        partyName = "Processor";
    }

    // process product as processor
    private void processProduct(Product product) {
        super.prepareProductToNextStage(product);
        System.out.println("Product state in processor is "+product.getState().getStateName());
        initProcessorParametres(product);
        System.out.println(product.getName()+" processor parametres: "+product.getProcessorParametres().toString());
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
     * process product as processor, sends it to next party in food
     * chain.
     * @param transaction
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

    // initialize parametres of product after processing
    private void initProcessorParametres(Product product) {
        if ((product.getName()).equalsIgnoreCase("apple")) {
            System.out.println("Process apple...");
            product.setProcessorParametres(APPLE_PROCESSING_TEMPERATURE, APPLE_CHEMICAL_PROCESSING_DEGREE);
        }
        else if ((product.getName()).equalsIgnoreCase("milk")) {
            System.out.println("Process milk...");
            product.setProcessorParametres(MILK_PROCESSING_TEMPERATURE, MILK_CHEMICAL_PROCESSING_DEGREE);
        }
        else if ((product.getName()).equalsIgnoreCase("pork")) {
            System.out.println("Process pork...");
            product.setProcessorParametres(PORK_PROCESSING_TEMPERATURE, PORK_CHEMICAL_PROCESSING_DEGREE);
        }
    }

}
