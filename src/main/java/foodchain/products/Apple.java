package foodchain.products;

import foodchain.states.GrowingState;
import java.util.HashMap;

public class Apple extends Product {

    public Apple() {
        this.state = new GrowingState();
        price = 20;
        name = "Apple";
        demoStorageParametres = new HashMap<String, Integer>();
        demoProcessorParametres = new HashMap<String, Integer>();
        distributionTime = 0;
        demoSellerParametres = new HashMap<String, String>();
    }

}
