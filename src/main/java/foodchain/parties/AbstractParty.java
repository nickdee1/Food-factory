package foodchain.parties;

import foodchain.FoodFactory;
import foodchain.MoneyTransaction;
import foodchain.Product;
import foodchain.ProductTransaction;
import foodchain.State;
import foodchain.Transaction;
import foodchain.channels.PaymentChannel;
import foodchain.channels.SellingChannel;
import java.util.List;

// must be ABSTRACT to avoid being instantiated directly
public abstract class AbstractParty implements Party {
    protected List<Transaction> transactionsList;
    protected List<Transaction> ownTransactionsList;
    protected List<Product> productsList;
    protected boolean moneyReceived;
    protected Product currentRequestedProduct;
    protected Party nextParty;
    protected Party currentRequestingParty;
    protected String name;
    
    public void setNext(Party next) {
        nextParty = next;
    }
    
    public void updateTransactions(Transaction transaction) {
        transactionsList.add(transaction);
    }
    
    public void makeRequest(String productName) {
        nextParty.getRequest(productName, this);
    }
    
    public void receiveMoney(MoneyTransaction transaction) {
        Integer receivedMoney = transaction.getMoneyAmount();
        if (receivedMoney.equals(currentRequestedProduct.getPrice())) {
            moneyReceived = true;
            transaction.setSuccessful(true);
            System.out.println(transaction.getReceiver().getName()+
                    " has received money: "+receivedMoney);
        }
        else {
            System.out.println("Not enough money!");
            transaction.setSuccessful(false);
        }
        ownTransactionsList.add(transaction);
        transaction.addParty(transaction.getSender());
        transaction.addParty(this);
        transaction.notifyAllParties();
    }
    
    public void getRequest(String productName, Party sender) {
        currentRequestingParty = sender;
        System.out.println("Current requested party: "+currentRequestingParty.getName());
        for (Product p : productsList) {
            if (p.getName().equalsIgnoreCase(productName)) {
                makeTransaction(currentRequestingParty, p);
                productsList.remove(p);
                // TO CHECK
                return;
            }
        }
        FoodFactory factory = new FoodFactory();
        currentRequestedProduct = factory.makeProduct(productName);
    }
    
    public void makeTransaction(Party receiver, Product product) {
        if (moneyReceived) {
            Transaction transaction = new ProductTransaction(receiver, this, null, product);
            SellingChannel channel = new SellingChannel(this, receiver);
            channel.makeTransmission(transaction);
            productsList.remove(product);
            moneyReceived = false;
            transaction.setSuccessful(true);
            ownTransactionsList.add(transaction);
        }
    }
    
    public void prepareProductToNextStage(Product product) {
        State storedState = product.getState();
        storedState.prepare(product);
        productsList.add(product);
    }
    
    public void receiveProduct(ProductTransaction transaction) {
        transaction.setSuccessful(true);
        ownTransactionsList.add(transaction);
        transaction.addParty(transaction.getSender());
        transaction.addParty(this);
        transaction.notifyAllParties();
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

    public String getName() {
        return name;
    }

}
