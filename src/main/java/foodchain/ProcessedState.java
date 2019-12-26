package foodchain;

public class ProcessedState extends State {
    
    public ProcessedState() {
       stateName = "Processed";
    }

    public void prepare(Product productContext) {
        productContext.setState(new DeliveredState());
    }
}
