package foodchain.products;

import foodchain.parties.Party;
import foodchain.states.AliveState;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class for pork product in simulation.
 */
public class Pork extends Product {

    /**
     * Constructs pork with default parameters.
     */
    public Pork() {
        this.state = new AliveState();
        this.statesHistory = new ArrayList<String>();
        statesHistory.add(this.state.getStateName());
        price = 80;
        name = "Pork";
        demoStorageParametres = new HashMap<String, Integer>();
        demoProcessorParametres = new HashMap<String, Integer>();
        currentlyProcessingParties = new ArrayList<Party>();
        demoSellerParametres = new HashMap<String, String>();
    }
}
