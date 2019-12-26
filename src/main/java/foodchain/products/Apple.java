package foodchain.products;

import foodchain.states.GrowingState;
import java.util.HashMap;

public class Apple extends Product {

    public Apple() {
        this.state = new GrowingState();
        price = 20;
        name = "Apple";
        storageParametres = new HashMap<String, Integer>();
        processorParametres = new HashMap<String, Integer>();
        distributionTime = 0;
        sellerParametres = new HashMap<String, String>();
    }

}