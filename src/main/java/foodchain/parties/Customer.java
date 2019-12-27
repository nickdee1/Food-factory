package foodchain.parties;

import foodchain.transactions.MoneyTransaction;
import foodchain.products.Product;
import foodchain.transactions.ProductTransaction;
import foodchain.transactions.Transaction;
import foodchain.reporters.PartiesReporter;
import java.util.ArrayList;

public class Customer extends AbstractParty {

    public Customer() {
        demoTransactionsList = new ArrayList<Transaction>();
        demoOwnTransactionsList = new ArrayList<Transaction>();
        demoProductsList = new ArrayList<Product>();
        partyName = "Customer";
        moneyReceived = false; // customer doesn't receive money
    }
    
    private void buyProduct(Product product) {
        super.prepareProductToNextStage(product);
        System.out.println("Product state in customer is "+product.getState().getStateName());
        System.out.println("PRODUCT IS CURRENTLY PROCESSED: "+
                            product.isIsCurrentlyProcessed());
        System.out.println("CONGRATULATIONS, FOOD CHAIN IS COMPLETED");
        System.out.println("--------------------------------------------------");
        addProduct(product);
    }

    public void acceptReporter(PartiesReporter partiesReporter) {
        partiesReporter.generateReportForParty(this);
    }

    @Override
    public void receiveProduct(ProductTransaction transaction) {
        super.receiveProduct(transaction);
        Product product = transaction.getProduct();
        System.out.println("Customer has received "+product.getName());
        buyProduct(product);
    }

    @Override
    public void receiveMoney(MoneyTransaction transaction) {
        System.out.println("Customer doesn't receive money, but pays!");
    }

    @Override
    protected void makeTransaction(Party receiver, Product product) {
        System.out.println("Customer doesn't send any products!");
    }

}
