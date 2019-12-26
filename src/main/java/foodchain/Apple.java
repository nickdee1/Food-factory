package foodchain;

import java.util.HashMap;

public class Apple extends Product {

    public Apple() {
        this.state = new GrowingState();
        price = 20;
        name = "apple";
        storageParametres = new HashMap<String, Integer>();
    }

}
