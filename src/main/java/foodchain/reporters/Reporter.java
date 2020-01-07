package foodchain.reporters;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Template for every reporter in chain.
 */
abstract class Reporter implements Visitor {

    /**
     * Method generates JSON file out of JSONObject.
     * @param object the JSONObject to be converted.
     * @param name the filename of output file.
     */
    public void generateJSON(JSONObject object, String name) {
        File path = new File("reports/");
        path.mkdir();

        String filepath = "reports/" + name + ".json";

        try {
            FileWriter file = new FileWriter(new File(filepath));
            file.write(object.toString(2));
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
