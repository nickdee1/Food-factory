package foodchain;

public class PackedState extends State {

    public PackedState() {
        stateName = "Packed";
    }

    public void prepare(Product productContext) {
        System.out.println("Product is already prepared!\n");
    }
}
