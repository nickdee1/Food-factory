package foodchain;

public class Pork extends Product {

    public Pork() {
        this.state = new AliveState();
        price = 80;
        name = "pork";
    }

}
