package foodchain;

public class PackedState extends State {

    public PackedState() {
        stateName = "Packed";
    }

    public void prepare(Product productContext) {
        productContext.setState(new SoldState());
    }
}
