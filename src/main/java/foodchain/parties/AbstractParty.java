package foodchain.parties;

import foodchain.FoodFactory;
import foodchain.transactions.MoneyTransaction;
import foodchain.products.Product;
import foodchain.transactions.ProductTransaction;
import foodchain.states.State;
import foodchain.transactions.Transaction;
import foodchain.channels.PaymentChannel;
import foodchain.channels.SellingChannel;
import java.util.List;
import com.google.common.collect.ImmutableList;

// must be ABSTRACT to avoid being instantiated directly
public abstract class AbstractParty implements Party {
    
    // demos are necessary to modify and then convert to immutable lists
    protected List<Transaction> demoTransactionsList;
    protected ImmutableList<Transaction> transactionsList;

    protected List<Transaction> demoOwnTransactionsList;
    protected ImmutableList<Transaction> ownTransactionsList;

    protected List<Product> demoProductsList;
    protected ImmutableList<Product> productsList;

    // basic variables
    protected boolean moneyReceived;
    protected Product currentRequestedProduct;
    protected Party currentRequestingParty;
    protected AbstractParty nextParty;
    protected String partyName;
    
    // double spending detection
    protected boolean attemptToDoubleSpend = false;
    protected Integer attemptsNumber = 0;

    public void setNext(AbstractParty next) {
        nextParty = next;
    }
    
    public void increaseAttempts() {
        attemptsNumber++;
    }
    
    public void setDoubleSpending() {
        attemptToDoubleSpend = true;
    }
    
    public void updateTransactions(Transaction transaction) {
        int size = demoTransactionsList.size();
        if (size == 0) {
            transaction.setPreviousTransaction(null);
        }
        else {
            transaction.setPreviousTransaction(demoTransactionsList.get(size-1));
        }
        demoTransactionsList.add(transaction);
        transactionsList = ImmutableList.copyOf(demoTransactionsList);
    }
    
    public void makeRequest(String productName) {
        nextParty.getRequest(productName, this);
    }

    public void receiveMoney(MoneyTransaction transaction) {
        Integer receivedMoney = transaction.getMoneyAmount();
        if (receivedMoney.equals(currentRequestedProduct.getPrice())) {
            moneyReceived = true;
            transaction.setSuccessful(true);
            System.out.println(transaction.getReceiver().getPartyName()+
                    " has received money: "+receivedMoney);
        }
        else {
            System.out.println("Not enough money!");
            transaction.setSuccessful(false);
        }
        addOwnTransaction(transaction);
        transaction.addParty(transaction.getSender());
        transaction.addParty(this);
        transaction.notifyAllParties();
    }
    
    private void getRequest(String productName, Party sender) {
        if ((this.getPartyName()).equalsIgnoreCase("customer")) {
            System.out.println("Customer doesn't get requests!");
            return;
        }
        currentRequestingParty = sender;
        System.out.println("Current requested party: "+currentRequestingParty.getPartyName());
        if (productsList != null) {
            for (Product p : productsList) {
                if (p.getName().equalsIgnoreCase(productName)) {
                    if (!p.getCurrentlyProcessingParties().contains(this)) {
                        p.addCurrentlyProcessingParties(this);
                    }
                    System.out.println(this.getPartyName()+" already has "+p.getName());
                    currentRequestedProduct = p;
                    currentRequestedProduct.setIsReadyToTransmit(true);
                    return;
                }
            }
        }
        currentRequestedProduct = FoodFactory.makeProduct(productName);
    }
    
    protected void makeTransaction(Party receiver, Product product) {
        if (moneyReceived) {
            Transaction transaction = new ProductTransaction(receiver, this, product);
            Transaction tmpTransaction = transaction;
            SellingChannel channel = new SellingChannel(receiver);
            transaction = channel.makeTransmission(transaction);
            if (transaction == null) {
                System.out.println("Something went wrong!");
                tmpTransaction.setSuccessful(false);
                tmpTransaction.addParty(this);
                tmpTransaction.addParty(receiver);
                addOwnTransaction(tmpTransaction);
                tmpTransaction.notifyAllParties();
            }
            else {
                product.setIsCurrentlyProcessed(true);
                transaction.setSuccessful(true);
                transaction.addParty(this);
                transaction.addParty(receiver);
                addOwnTransaction(transaction);
                transaction.notifyAllParties();
            }
            moneyReceived = false;
        }
    }
    
    public void prepareProductToNextStage(Product product) {
        State storedState = product.getState();
        storedState.prepare(product);
        demoProductsList.add(product);
    }
    
    public void receiveProduct(ProductTransaction transaction) {
        Product product = transaction.getProduct();
        product.addCurrentlyProcessingParties(this);
    }
    
    public void makeTransaction(Integer money) {
        Transaction transaction = new MoneyTransaction(nextParty, this, money);
        Transaction tmpTransaction = transaction;
        PaymentChannel channel = new PaymentChannel(nextParty);
        transaction = channel.makeTransmission(transaction);
        if (transaction == null) {
            System.out.println("Something went wrong!");
            tmpTransaction.setSuccessful(false);
            addOwnTransaction(tmpTransaction);
        }
        else addOwnTransaction(transaction);
    }

    private void addOwnTransaction(Transaction transaction) {
        int size = demoOwnTransactionsList.size();
        if (size == 0) {
            transaction.setPreviousTransaction(null);
        }
        else {
            transaction.setPreviousTransaction(demoOwnTransactionsList.get(size-1));
        }
        demoOwnTransactionsList.add(transaction);
        ownTransactionsList = ImmutableList.copyOf(demoOwnTransactionsList);
    }

    protected void addProduct(Product product) {
        demoProductsList.add(product);
        productsList = ImmutableList.copyOf(demoProductsList);
    }
    
    public void removeProduct(Product product) {
        demoProductsList.remove(product);
        productsList = ImmutableList.copyOf(demoProductsList);
    }

    public String getPartyName() {
        return partyName;
    }

    public ImmutableList<Transaction> getTransactionsList() {
        return transactionsList;
    }

    public ImmutableList<Transaction> getOwnTransactionsList() {
        return ownTransactionsList;
    }

    public ImmutableList<Product> getProductsList() {
        return productsList;
    }
    
    // for simulation
    public void addProductToList(Product product) {
        addProduct(product);
    }
}
