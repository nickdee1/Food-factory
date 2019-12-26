package foodchain;

public class CollectedState extends State {

    public CollectedState() {
        stateName = "Collected";
    }

    public void prepare(Product productContext) {
        productContext.setState(new StoredState());
    }
}
