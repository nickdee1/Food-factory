package foodchain.parties;

import foodchain.transactions.MoneyTransaction;
import foodchain.products.Product;
import foodchain.transactions.ProductTransaction;
import foodchain.transactions.Transaction;
import foodchain.reporters.PartiesReporter;
import java.util.ArrayList;
import java.util.LinkedList;

import static foodchain.parties.Data.*;


public class Seller extends AbstractParty {

    public Seller() {
        transactionsList = new LinkedList<Transaction>();
        ownTransactionsList = new LinkedList<Transaction>();
        productsList = new ArrayList<Product>();
        partyName = "Seller";
    }

    public void sellProduct(Product product) {
        super.prepareProductToNextStage(product);
        System.out.println("Product state in seller is "+product.getState().getStateName());
        initSellerParametres(product);
        System.out.println(product.getName()+" seller parametres: "+product.getSellerParametres().toString());
        productsList.add(product);
    }

    public void acceptReporter(PartiesReporter partiesReporter) {
        partiesReporter.generateReportForParty(this);
    }

    @Override
    public void receiveProduct(ProductTransaction transaction) {
        super.receiveProduct(transaction);
        Product product = transaction.getProduct();
        System.out.println("Seller has received "+product.getName());
        sellProduct(product);
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

    private void initSellerParametres(Product product) {
        if ((product.getName()).equalsIgnoreCase("apple")) {
            System.out.println("Apple seller parametres: "+product.getSellerParametres().toString());
            System.out.println("Sell apple...");
            product.setSellerParametres(APPLE_PACKAGING, APPLE_SELLING_PLACE);
        }
        else if ((product.getName()).equalsIgnoreCase("pork")) {
            System.out.println("Pork seller parametres: "+product.getSellerParametres().toString());
            System.out.println("Sell pork...");
            product.setSellerParametres(PORK_PACKAGING, PORK_SELLING_PLACE);
        }
        else if ((product.getName()).equalsIgnoreCase("milk")) {
            System.out.println("Milk seller parametres: "+product.getSellerParametres().toString());
            System.out.println("Sell milk...");
            product.setSellerParametres(MILK_PACKAGING, MILK_SELLING_PLACE);
        }
    }

}
