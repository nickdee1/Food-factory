package foodchain;

public class Apple extends Product {

    public Apple() {
        this.state = new GrowingState();
        price = 20;
        name = "apple";
    }

}
