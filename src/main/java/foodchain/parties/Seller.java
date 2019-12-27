package foodchain.parties;

import foodchain.transactions.MoneyTransaction;
import foodchain.products.Product;
import foodchain.transactions.ProductTransaction;
import foodchain.transactions.Transaction;
import foodchain.reporters.PartiesReporter;
import java.util.ArrayList;

import static foodchain.parties.Data.*;

public class Seller extends AbstractParty {

    public Seller() {
        demoTransactionsList = new ArrayList<Transaction>();
        demoOwnTransactionsList = new ArrayList<Transaction>();
        demoProductsList = new ArrayList<Product>();
        partyName = "Seller";
    }

    private void sellProduct(Product product) {
        super.prepareProductToNextStage(product);
        System.out.println("Product state in seller is "+product.getState().getStateName());
        initSellerParametres(product);
        System.out.println(product.getName()+" seller parametres: "+product.getSellerParametres().toString());
        addProduct(product);
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
    
    private void sendProduct(Product product) {
        if (currentRequestedProduct != null) {
            makeTransaction(currentRequestingParty, product);
            currentRequestedProduct = null;
            currentRequestingParty = null;
        }
    }

    private void initSellerParametres(Product product) {
        if ((product.getName()).equalsIgnoreCase("apple")) {
            System.out.println("Sell apple...");
            product.setSellerParametres(APPLE_PACKAGING, APPLE_SELLING_PLACE);
        }
        else if ((product.getName()).equalsIgnoreCase("pork")) {
            System.out.println("Sell pork...");
            product.setSellerParametres(PORK_PACKAGING, PORK_SELLING_PLACE);
        }
        else if ((product.getName()).equalsIgnoreCase("milk")) {
            System.out.println("Sell milk...");
            product.setSellerParametres(MILK_PACKAGING, MILK_SELLING_PLACE);
        }
    }
}
