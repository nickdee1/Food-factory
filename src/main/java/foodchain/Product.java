package foodchain;

import foodchain.reporters.ProductReporter;
import java.util.Map;

public abstract class Product {
    
    protected State state;
    protected Integer price;
    protected String name;
    
    protected Map<String, Integer> storageParametres;
    
    public void accept(ProductReporter reporter) {
        
    }
    
    public void setState(State state) {
        this.state = state;
    }
    
    public State getState() {
        return state;
    }
    
    public Integer getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
    
    public void setStorageParametres(Integer storageTime, Integer storageTemperature,
            Integer storageHumidity) {
        storageParametres.put("Storage Time", storageTime);
        storageParametres.put("Storage Temperature", storageTemperature);
        storageParametres.put("Storage Humidity", storageHumidity);
    }

    public Map<String, Integer> getStorageParametres() {
        return storageParametres;
    }
    
}
