package foodchain;

import foodchain.reporters.ProductReporter;
import java.util.Map;

public abstract class Product {
    
    protected State state;
    protected Integer price;
    protected String name;
    
    protected Map<String, Integer> storageParametres;
    protected Map<String, Integer> processorParametres;
    protected Integer distributionTime;
    protected Map<String, String> sellerParametres;
    
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
    
    public void setProcessorParametres(Integer processorTemperature, Integer
            chemicalDegree) {
        processorParametres.put("Processing Temperature", processorTemperature);
        processorParametres.put("Chemical Processing Degree", chemicalDegree);
    }

    public Map<String, Integer> getProcessorParametres() {
        return processorParametres;
    }
    
    public void setSellerParametres(String packaging, String sellingPlace) {
        sellerParametres.put("Type of Packaging", packaging);
        sellerParametres.put("Selling Place", sellingPlace);
    }

    public Map<String, String> getSellerParametres() {
        return sellerParametres;
    }
    
    public void increaseDistributionTime() {
        
        distributionTime++;
    }

    public Integer getDistributionTime() {
        return distributionTime;
    }
}
