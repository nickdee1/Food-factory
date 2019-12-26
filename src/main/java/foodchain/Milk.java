package foodchain;

import java.util.HashMap;

public class Milk extends Product {
    
    public Milk() {
        this.state = new CollectedState();
        price = 45;
        name = "Milk";
        storageParametres = new HashMap<String, Integer>();
        processorParametres = new HashMap<String, Integer>();
        distributionTime = 0;
        sellerParametres = new HashMap<String, String>();
    }
}
