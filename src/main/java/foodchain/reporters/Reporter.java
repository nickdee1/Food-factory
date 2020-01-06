package foodchain.reporters;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

abstract class Reporter implements Visitor{

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
