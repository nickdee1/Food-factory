package foodchain.reporters;

import org.json.JSONObject;

/**
 * Interface for reporters
 */
public interface Visitor {

    /**
     * Method generates JSON file out of JSONObject
     * @param object the JSONObject to be converted
     * @param name the filename of output file
     */
    void generateJSON(JSONObject object, String name);
}
