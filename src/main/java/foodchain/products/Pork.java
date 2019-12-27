package foodchain.products;

import foodchain.parties.Party;
import foodchain.states.AliveState;
import java.util.ArrayList;
import java.util.HashMap;

public class Pork extends Product {

    public Pork() {
        this.state = new AliveState();
        price = 80;
        name = "Pork";
        demoStorageParametres = new HashMap<String, Integer>();
        demoProcessorParametres = new HashMap<String, Integer>();
        currentlyProcessingParties = new ArrayList<Party>();
        distributionTime = 0;
        demoSellerParametres = new HashMap<String, String>();
    }

}
