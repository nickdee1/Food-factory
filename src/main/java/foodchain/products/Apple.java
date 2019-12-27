package foodchain.products;

import foodchain.parties.Party;
import foodchain.states.GrowingState;
import java.util.ArrayList;
import java.util.HashMap;

public class Apple extends Product {

    public Apple() {
        this.state = new GrowingState();
        price = 20;
        name = "Apple";
        demoStorageParametres = new HashMap<String, Integer>();
        demoProcessorParametres = new HashMap<String, Integer>();
        currentlyProcessingParties = new ArrayList<Party>();
        demoSellerParametres = new HashMap<String, String>();
    }
}
