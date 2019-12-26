package foodchain;

import java.util.HashMap;

public class Pork extends Product {

    public Pork() {
        this.state = new AliveState();
        price = 80;
        name = "pork";
        storageParametres = new HashMap<String, Integer>();
    }

}
