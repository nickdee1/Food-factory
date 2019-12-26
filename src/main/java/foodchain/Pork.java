package foodchain;

import java.util.HashMap;

public class Pork extends Product {

    public Pork() {
        this.state = new AliveState();
        price = 80;
        name = "Pork";
        storageParametres = new HashMap<String, Integer>();
        processorParametres = new HashMap<String, Integer>();
        distributionTime = 0;
        sellerParametres = new HashMap<String, String>();
    }

}
