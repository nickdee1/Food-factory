package foodchain.states;

import foodchain.parties.Party;
import foodchain.products.Product;

public class PackedState extends State {

    public PackedState() {
        stateName = "Packed";
    }

    public void prepare(Product productContext) {
        productContext.setState(new SoldState());
        productContext.setIsCurrentlyProcessed(false);
        for (Party p : productContext.getCurrentlyProcessingParties()) {
            p.removeProduct(productContext);
        }
        productContext.clearPartyList();
    }
}
