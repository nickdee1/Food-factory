package foodchain.parties;

import foodchain.MoneyTransaction;
import foodchain.Product;
import foodchain.ProductTransaction;
import foodchain.Transaction;
import foodchain.channels.PaymentChannel;
import foodchain.reporters.PartiesReporter;
import java.util.LinkedList;
import java.util.ArrayList;


public class Storage extends AbstractParty {

    public Storage() {
        transactionsList = new LinkedList<Transaction>();
        ownTransactionsList = new LinkedList<Transaction>();
        productsList = new ArrayList<Product>();
        moneyReceived = false;
    }

    public void store(Product product) {
        super.prepareProductToNextStage(product);
        System.out.println("Product state in storage is "+product.getState().getStateName());
        // TODO ELSE
    }

    public void acceptReporter(PartiesReporter partiesReporter) {
        partiesReporter.generateReportForStorage(this);
    }

    public void makeTransaction(Integer money) {
        Transaction transaction = new MoneyTransaction(nextParty, this, null, money);
        PaymentChannel channel = new PaymentChannel(this, nextParty);
        transaction = channel.makeTransmission(transaction);
        if (!transaction.isSuccessful()) {
            System.out.println("I did something wrong!");
        }
        ownTransactionsList.add(transaction);
    }

    @Override
    public void receiveProduct(ProductTransaction transaction) {
        super.receiveProduct(transaction);
        Product product = transaction.getProduct();
        System.out.println("Storage has received "+product.getName());
        store(product);
        if (currentRequestedProduct != null) {
            makeTransaction(currentRequestingParty, product);
            currentRequestedProduct = null;
            currentRequestingParty = null;
        }
    }
    
    @Override
    public void receiveMoney(MoneyTransaction transaction) {
        super.receiveMoney(transaction);
        Integer receivedMoney = transaction.getMoneyAmount();
        if (receivedMoney.equals(currentRequestedProduct.getPrice())) {
            makeRequest(currentRequestedProduct.getName(), this);
            makeTransaction(receivedMoney);
        }
    }

}
