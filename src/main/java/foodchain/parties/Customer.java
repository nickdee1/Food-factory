package foodchain.parties;

import foodchain.transactions.MoneyTransaction;
import foodchain.products.Product;
import foodchain.transactions.ProductTransaction;
import foodchain.transactions.Transaction;
import java.util.ArrayList;

/**
 * Customer class in simulation.
 */
public class Customer extends AbstractParty {

    /**
     * Construct customer party.
     */
    public Customer() {
        currentRequestingParty = null;
        demoTransactionsList = new ArrayList<>();
        demoOwnTransactionsList = new ArrayList<>();
        demoProductsList = new ArrayList<>();
        partyName = "Customer";
        partyType = PartyType.CUSTOMER;
        moneyReceived = false; // customer doesn't receive money
    }

    /**
     * Process product as customer.
     * @param product the  product to be processed.
     */
    private void buyProduct(Product product) {
        super.prepareProductToNextStage(product);
        System.out.println("Product state in customer is "+product.getState().getStateName());
        System.out.println("CONGRATULATIONS, FOOD CHAIN IS COMPLETED");
        System.out.println("--------------------------------------------------");
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
        System.out.println("Customer has received "+product.getName());
        buyProduct(product);
    }

    /**
     * Receives money transaction from payment channel.
     * @param transaction the transaction to process.
     */
    @Override
    public void receiveMoney(MoneyTransaction transaction) {
        System.err.println("Customer doesn't receive money, but pays!");
    }

    /**
     * Transmits product to party-receiver by selling channel (only
     * if money for this product were received).
     * @param receiver the party-receiver to which the product is sent.
     * @param product the product to send.
     */
    @Override
    protected void makeTransaction(Party receiver, Product product) {
        System.err.println("Customer doesn't sell any products!");
    }

}
