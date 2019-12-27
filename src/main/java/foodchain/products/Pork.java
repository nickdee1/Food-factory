package foodchain.products;

import foodchain.states.AliveState;
import java.util.HashMap;

public class Pork extends Product {

    public Pork() {
        this.state = new AliveState();
        price = 80;
        name = "Pork";
        demoStorageParametres = new HashMap<String, Integer>();
        demoProcessorParametres = new HashMap<String, Integer>();
        distributionTime = 0;
        demoSellerParametres = new HashMap<String, String>();
    }

}
