package foodchain.products;

import foodchain.states.State;
import foodchain.reporters.ProductReporter;
import java.util.Map;
import com.google.common.collect.ImmutableMap; 
import foodchain.parties.Party;
import java.util.List;

public abstract class Product {
    
    protected State state;
    protected Integer price;
    protected String name;
    protected boolean isReadyToTransmit;
    protected boolean isCurrentlyProcessed;
    
    protected List<Party> currentlyProcessingParties;
    
    protected Map<String, Integer> demoStorageParametres;
    protected ImmutableMap<String, Integer> storageParametres;
    
    protected Map<String, Integer> demoProcessorParametres;
    protected ImmutableMap<String, Integer> processorParametres;
    
    protected Integer distributionTime;
    
    protected Map<String, String> demoSellerParametres;
    protected ImmutableMap<String, String> sellerParametres;
    
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

    public List<Party> getCurrentlyProcessingParties() {
        return currentlyProcessingParties;
    }

    public boolean isIsCurrentlyProcessed() {
        return isCurrentlyProcessed;
    }
    
    public void clearPartyList() {
        currentlyProcessingParties.clear();
    }

    public void addCurrentlyProcessingParties(Party party) {
        currentlyProcessingParties.add(party);
    }

    public void setIsCurrentlyProcessed(boolean isCurrentlyProcessed) {
        this.isCurrentlyProcessed = isCurrentlyProcessed;
    }

    public boolean isIsReadyToTransmit() {
        return isReadyToTransmit;
    }

    public void setIsReadyToTransmit(boolean isReadyToTransmit) {
        this.isReadyToTransmit = isReadyToTransmit;
    }
    
    public void setStorageParametres(Integer storageTime, Integer storageTemperature,
            Integer storageHumidity) {
        demoStorageParametres.put("Storage Time", storageTime);
        demoStorageParametres.put("Storage Temperature", storageTemperature);
        demoStorageParametres.put("Storage Humidity", storageHumidity);
        storageParametres = ImmutableMap.copyOf(demoStorageParametres);
        demoStorageParametres.clear();
    }

    public ImmutableMap<String, Integer> getStorageParametres() {
        return storageParametres;
    }
    
    public void setProcessorParametres(Integer processorTemperature, Integer
            chemicalDegree) {
        demoProcessorParametres.put("Processing Temperature", processorTemperature);
        demoProcessorParametres.put("Chemical Processing Degree", chemicalDegree);
        processorParametres = ImmutableMap.copyOf(demoProcessorParametres);
        demoProcessorParametres.clear();
    }

    public ImmutableMap<String, Integer> getProcessorParametres() {
        return processorParametres;
    }
    
    public void setSellerParametres(String packaging, String sellingPlace) {
        demoSellerParametres.put("Type of Packaging", packaging);
        demoSellerParametres.put("Selling Place", sellingPlace);
        sellerParametres = ImmutableMap.copyOf(demoSellerParametres);
        demoSellerParametres.clear();
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
