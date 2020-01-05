package foodchain.reporters;

import org.json.JSONObject;

public interface Visitor {
    void generateJSON(JSONObject object, String name);
}
