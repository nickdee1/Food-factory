package foodchain.products;

import foodchain.parties.Party;
import foodchain.states.green.GrowingState;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class for apple product in simulation.
 */
public class Apple extends Product {

    /**
     * Constructs apple with default parameters.
     */
    public Apple() {
        this.state = new GrowingState();
        this.statesHistory = new ArrayList<String>();
        statesHistory.add(this.state.getStateName());
        price = 20;
        name = "Apple";
        demoStorageParametres = new HashMap<String, Integer>();
        demoProcessorParametres = new HashMap<String, Integer>();
        currentlyProcessingParties = new ArrayList<Party>();
        demoSellerParametres = new HashMap<String, String>();
    }
}
