package foodchain;

public class DeliveredState extends State {

    public DeliveredState() {
        stateName = "Delivered";
    }
    
    public void prepare(Product productContext) {
        productContext.setState(new PackedState());
    }
}
