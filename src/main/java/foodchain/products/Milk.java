package foodchain.products;

import foodchain.states.CollectedState;
import java.util.HashMap;

public class Milk extends Product {
    
    public Milk() {
        this.state = new CollectedState();
        price = 45;
        name = "Milk";
        demoStorageParametres = new HashMap<String, Integer>();
        demoProcessorParametres = new HashMap<String, Integer>();
        distributionTime = 0;
        demoSellerParametres = new HashMap<String, String>();
    }
}
