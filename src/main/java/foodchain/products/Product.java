package foodchain.products;

import foodchain.states.State;
import foodchain.reporters.ProductReporter;

import java.util.ArrayList;
import java.util.Map;
import com.google.common.collect.ImmutableMap; 
import foodchain.parties.Party;
import java.util.List;

/**
 * Abstract class representing product processed in a food chain.
 */
public abstract class Product {
    
    /**
     * State of product in different parties
     */
    protected State state;

    protected Integer price;
    protected String name;

    /**
     * True if some party already has this product and it is not necessary
     * to make it in food factory
     */
    protected boolean isReadyToTransmit;
    protected boolean isCurrentlyProcessed;
    
    /**
     * List of parties in a food chain which are currently involved
     * in processing of this product
     */
    protected List<Party> currentlyProcessingParties;
    
    // demos are necessary to modify and then convert to immutable maps

    /**
     * Map of product's storing characteristic to modify.
     */
    protected Map<String, Integer> demoStorageParametres;

    /**
     * Immutable map of product's storing characteristics.
     */
    protected ImmutableMap<String, Integer> storageParametres;
    
    /**
     * Map of product's processing characteristic to modify.
     */
    protected Map<String, Integer> demoProcessorParametres;

    /**
     * Immutable map of product's processing characteristics.
     */
    protected ImmutableMap<String, Integer> processorParametres;
    
    /**
     * Map of product's selling characteristic to modify.
     */
    protected Map<String, String> demoSellerParametres;

    /**
     * Immutable map of product's selling characteristics.
     */
    protected ImmutableMap<String, String> sellerParametres;

    /**
     *
     * @param state
     */
    public void setState(State state) {
        this.state = state;
    }
    
    /**
     *
     * @return product's current state
     */
    public State getState() {
        return state;
    }
    
    /**
     *
     * @return product's price
     */
    public Integer getPrice() {
        return price;
    }

    /**
     *
     * @return product's name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return list of parties in a food chain which are currently involved
     * in processing of this product
     */
    public List<Party> getCurrentlyProcessingParties() {
        return currentlyProcessingParties;
    }

    /**
     *
     * @return
     */
    public boolean isIsCurrentlyProcessed() {
        return isCurrentlyProcessed;
    }
    
    /**
     * Clears list of currently processing parties of product
     */
    public void clearPartyList() {
        currentlyProcessingParties.clear();
    }

    /**
     * Adds party in list of currently processing parties of product
     * @param party
     */
    public void addCurrentlyProcessingParties(Party party) {
        currentlyProcessingParties.add(party);
    }

    /**
     *
     * @param isCurrentlyProcessed
     */
    public void setIsCurrentlyProcessed(boolean isCurrentlyProcessed) {
        this.isCurrentlyProcessed = isCurrentlyProcessed;
    }

    /**
     *
     * @return
     */
    public boolean isIsReadyToTransmit() {
        return isReadyToTransmit;
    }

    /**
     *
     * @param isReadyToTransmit
     */
    public void setIsReadyToTransmit(boolean isReadyToTransmit) {
        this.isReadyToTransmit = isReadyToTransmit;
    }
    
    /**
     * Set product's storing characteristics.
     * @param storageTime
     * @param storageTemperature
     * @param storageHumidity
     */
    public void setStorageParametres(Integer storageTime, Integer storageTemperature,
            Integer storageHumidity) {
        demoStorageParametres.put("storage_time", storageTime);
        demoStorageParametres.put("storage_temperature", storageTemperature);
        demoStorageParametres.put("storage_humidity", storageHumidity);
        storageParametres = ImmutableMap.copyOf(demoStorageParametres);
        demoStorageParametres.clear();
    }

    /**
     *
     * @return map of product's storing characteristics
     */
    public ImmutableMap<String, Integer> getStorageParametres() {
        return storageParametres;
    }
    
    /**
     * Set product's processing characteristics.
     * @param processorTemperature
     * @param chemicalDegree
     */
    public void setProcessorParametres(Integer processorTemperature, Integer
            chemicalDegree) {
        demoProcessorParametres.put("processing_temperature", processorTemperature);
        demoProcessorParametres.put("chemical_processing_degree", chemicalDegree);
        processorParametres = ImmutableMap.copyOf(demoProcessorParametres);
        demoProcessorParametres.clear();
    }

    /**
     *
     * @return map of product's processing characteristics
     */
    public ImmutableMap<String, Integer> getProcessorParametres() {
        return processorParametres;
    }
    
    /**
     * Set product's selling characteristics.
     * @param packaging
     * @param sellingPlace
     */
    public void setSellerParametres(String packaging, String sellingPlace) {
        demoSellerParametres.put("type_of_packaging", packaging);
        demoSellerParametres.put("selling_place", sellingPlace);
        sellerParametres = ImmutableMap.copyOf(demoSellerParametres);
        demoSellerParametres.clear();
    }

    /**
     *
     * @return map of product's selling characteristics
     */
    public Map<String, String> getSellerParametres() {
        return sellerParametres;
    }


    /**
     * @return
     */
    public List<Map> getAllParameters() {
        List<Map> allProductParameters = new ArrayList<Map>();
        if (processorParametres != null) allProductParameters.add(processorParametres);
        if (sellerParametres != null) allProductParameters.add(sellerParametres);
        if (storageParametres != null) allProductParameters.add(storageParametres);

        return allProductParameters;
    }
}
