package foodchain.parties;

import foodchain.MoneyTransaction;
import foodchain.Product;
import foodchain.ProductTransaction;
import foodchain.Transaction;
import foodchain.channels.PaymentChannel;
import foodchain.reporters.PartiesReporter;
import java.util.ArrayList;
import java.util.LinkedList;


public class Processor extends AbstractParty {

    public Processor() {
        transactionsList = new LinkedList<Transaction>();
        ownTransactionsList = new LinkedList<Transaction>();
        productsList = new ArrayList<Product>();
    }

    public void processProduct(Product product) {
        super.prepareProductToNextStage(product);
        System.out.println("Product state in processor is "+product.getState().getStateName());
        // TODO ELSE
    }

    @Override
    public void acceptReporter(PartiesReporter partiesReporter) {
        partiesReporter.generateReportForProcessor(this);
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

    public void receiveProduct(ProductTransaction transaction) {
        transaction.setSuccessful(true);
        ownTransactionsList.add(transaction);
        Product product = transaction.getProduct();
        System.out.println("Processor has received "+product.getName());
        transaction.addParty(transaction.getSender());
        transaction.addParty(this);
        transaction.notifyAllParties();
        processProduct(product);
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
        if (currentRequestedProduct != null &&
                receivedMoney.equals(currentRequestedProduct.getPrice())) {
            makeRequest(currentRequestedProduct.getName(), this);
            makeTransaction(receivedMoney);
        }
    }

}
