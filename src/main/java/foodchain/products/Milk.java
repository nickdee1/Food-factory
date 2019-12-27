package foodchain.products;

import foodchain.parties.Party;
import foodchain.states.CollectedState;
import java.util.ArrayList;
import java.util.HashMap;

public class Milk extends Product {
    
    public Milk() {
        this.state = new CollectedState();
        price = 45;
        name = "Milk";
        demoStorageParametres = new HashMap<String, Integer>();
        demoProcessorParametres = new HashMap<String, Integer>();
        currentlyProcessingParties = new ArrayList<Party>();
        demoSellerParametres = new HashMap<String, String>();
    }
}
