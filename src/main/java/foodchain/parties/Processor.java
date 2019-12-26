package foodchain.parties;

import foodchain.transactions.MoneyTransaction;
import foodchain.products.Product;
import foodchain.transactions.ProductTransaction;
import foodchain.transactions.Transaction;
import foodchain.reporters.PartiesReporter;
import java.util.ArrayList;
import java.util.LinkedList;

import static foodchain.parties.Data.*;


public class Processor extends AbstractParty {

    public Processor() {
        transactionsList = new LinkedList<Transaction>();
        ownTransactionsList = new LinkedList<Transaction>();
        productsList = new ArrayList<Product>();
        partyName = "Processor";
    }

    public void processProduct(Product product) {
        super.prepareProductToNextStage(product);
        System.out.println("Product state in processor is "+product.getState().getStateName());
        initProcessorParametres(product);
        System.out.println(product.getName()+" processor parametres: "+product.getProcessorParametres().toString());
        productsList.add(product);
    }

    public void acceptReporter(PartiesReporter partiesReporter) {
        partiesReporter.generateReportForProcessor(this);
    }

    @Override
    public void receiveProduct(ProductTransaction transaction) {
        super.receiveProduct(transaction);
        Product product = transaction.getProduct();
        System.out.println("Processor has received "+product.getName());
        processProduct(product);
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

    private void initProcessorParametres(Product product) {
        if ((product.getName()).equalsIgnoreCase("apple")) {
            System.out.println("Apple processor parametres: "+product.getProcessorParametres().toString());
            System.out.println("Process apple...");
            product.setProcessorParametres(APPLE_PROCESSING_TEMPERATURE, APPLE_CHEMICAL_PROCESSING_DEGREE);
        }
        else if ((product.getName()).equalsIgnoreCase("milk")) {
            System.out.println("Milk processor parametres: "+product.getProcessorParametres().toString());
            System.out.println("Process milk...");
            product.setProcessorParametres(MILK_PROCESSING_TEMPERATURE, MILK_CHEMICAL_PROCESSING_DEGREE);
        }
        else if ((product.getName()).equalsIgnoreCase("pork")) {
            System.out.println("Pork processor parametres: "+product.getProcessorParametres().toString());
            System.out.println("Process pork...");
            product.setProcessorParametres(PORK_PROCESSING_TEMPERATURE, PORK_CHEMICAL_PROCESSING_DEGREE);
        }
    }

}
