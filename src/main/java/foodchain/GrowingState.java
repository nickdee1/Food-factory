package foodchain;

public class GrowingState extends State {

    public GrowingState() {
        stateName = "Growing";
    }
    
    public void prepare(Product productContext) {
        productContext.setState(new CollectedState());
    }
}
