package foodchain.products;

import foodchain.parties.Party;
import foodchain.states.green.CollectedState;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class for milk product in simulation.
 */
public class Milk extends Product {

    /**
     * Constructs milk with default parameters.
     */
    public Milk() {
        this.state = new CollectedState();
        this.statesHistory = new ArrayList<String>();
        statesHistory.add(this.state.getStateName());
        price = 45;
        name = "Milk";
        demoStorageParametres = new HashMap<String, Integer>();
        demoProcessorParametres = new HashMap<String, Integer>();
        currentlyProcessingParties = new ArrayList<Party>();
        demoSellerParametres = new HashMap<String, String>();
    }
}
