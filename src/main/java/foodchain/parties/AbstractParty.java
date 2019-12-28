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

/**
 * Abstract class representing common attributes and methods for each party.
 * Must be abstract to avoid being instantiated directly.
 */
public abstract class AbstractParty implements Party {
    
    // demos are necessary to modify and then convert to immutable lists

    /**
     * List of all transactions in the food chain to modify
     */
    protected List<Transaction> demoTransactionsList;

    /**
     * Immutable list of all transactions in the food chain
     */
    protected ImmutableList<Transaction> transactionsList;

    /**
     * List of all transactions of certain party to modify
     */
    protected List<Transaction> demoOwnTransactionsList;

    /**
     * Immutable list of all transactions of certain party
     */
    protected ImmutableList<Transaction> ownTransactionsList;

    /**
     * List of all products of certain party to modify
     */
    protected List<Product> demoProductsList;

    /**
     * Immutable list of all products of certain party to modify
     */
    protected ImmutableList<Product> productsList;

    // basic variables

    protected boolean moneyReceived;
    protected Product currentRequestedProduct;
    protected Party currentRequestingParty;

    /**
     * Next party in chain of responsibility
     */
    protected AbstractParty nextParty;
    protected String partyName;
    
    // double spending detection

    protected boolean attemptToDoubleSpend = false;
    protected Integer attemptsNumber = 0;

    /**
     *
     * @param next - next party in chain of responsibility
     */
    public void setNext(AbstractParty next) {
        nextParty = next;
    }
    
    /**
     * Increases number of attempts to commit double-spending
     */
    public void increaseAttempts() {
        attemptsNumber++;
    }
    
    /**
     * Set boolean attemptToDoubleSpend to true
     */
    public void setDoubleSpending() {
        attemptToDoubleSpend = true;
    }
    
    /**
     * Adds recent transaction made somewhere in food chain
     * to the list of transactions of whole food chain of
     * current party.
     * @param transaction
     */
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
    
    /**
     * Ask next party in chain of responsibility for product
     * defined by its name.
     * @param productName
     */
    public void makeRequest(String productName) {
        nextParty.getRequest(productName, this);
    }

    /**
     * Receives money transaction from payment channel.
     * @param transaction
     */
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
    
    // gets request to make product defined by its name from party-sender
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
    
    /**
     * Transmits product to party-receiver by selling channel (only
     * if money for this product were received).
     * @param receiver
     * @param product
     */
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
    
    /**
     * Changes product's state to the next one in its processing.
     * @param product
     */
    public void prepareProductToNextStage(Product product) {
        State storedState = product.getState();
        storedState.prepare(product);
        demoProductsList.add(product);
    }
    
    /**
     * Receives product transaction transmitted by selling channel.
     * @param transaction
     */
    public void receiveProduct(ProductTransaction transaction) {
        Product product = transaction.getProduct();
        product.addCurrentlyProcessingParties(this);
    }
    
    /**
     * Send money to party which is currently requested to make
     * product by payment channel.
     * @param money
     */
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

    // adds transaction to list of party's own transactions
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

    /**
     * Adds product in list of party's products.
     * @param product
     */
    protected void addProduct(Product product) {
        demoProductsList.add(product);
        productsList = ImmutableList.copyOf(demoProductsList);
    }
    
    /**
     * Removes product from list of party's products.
     * @param product
     */
    public void removeProduct(Product product) {
        demoProductsList.remove(product);
        productsList = ImmutableList.copyOf(demoProductsList);
    }

    /**
     *
     * @return name of party
     */
    public String getPartyName() {
        return partyName;
    }

    /**
     *
     * @return list of all transactions in whole food chain
     */
    public ImmutableList<Transaction> getTransactionsList() {
        return transactionsList;
    }

    /**
     *
     * @return list of all transactions of current party
     */
    public ImmutableList<Transaction> getOwnTransactionsList() {
        return ownTransactionsList;
    }

    /**
     *
     * @return list of all products of current party
     */
    public ImmutableList<Product> getProductsList() {
        return productsList;
    }

    /**
     * Temporary function for simulation.
     * @param product to add in list of all products of current party
     */
    public void addProductToList(Product product) {
        addProduct(product);
    }
}
