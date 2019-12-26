package foodchain;

import foodchain.states.GrowingState;
import foodchain.states.State;
import foodchain.states.AliveState;
import foodchain.products.Apple;
import foodchain.products.Milk;
import foodchain.products.Product;
import foodchain.products.Pork;

public class FoodFactory {

    public Product makeProduct(String productName) {
        if (productName.equalsIgnoreCase("milk")) {
            Product milk = new Milk();
            // milk doesn't need to be prepared on this stage
            return milk;
        }
        else if (productName.equalsIgnoreCase("pork")) {
            Product pork = new Pork();
            State alive = new AliveState();
            alive.prepare(pork);
            return pork;
        }
        else if (productName.equalsIgnoreCase("apple")) {
            Product apple = new Apple();
            State growing = new GrowingState();
            growing.prepare(apple);
            return apple;
        }
        return null;
    }

}
