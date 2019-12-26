package foodchain.reporters;

import foodchain.Product;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ProductReporter implements Visitor {

    public void generateReportForProduct(Product pork) {
        String name = pork.getName();
        String state = pork.getState().getStateName();
        Integer price = pork.getPrice();

        JSONObject jsonOut = new JSONObject();
        jsonOut.put("name", name);
        jsonOut.put("current_state", state);
        jsonOut.put("price", price);

        generateJSON(jsonOut, name);
    }


    // TODO: - Generate report for all products
    public void generateForAll() {

    }

    private void generateJSON(JSONObject object, String name) {
        String filepath = "reports/" + name + ".json";

        try {
            FileWriter file = new FileWriter(new File(filepath));
            file.write(object.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
