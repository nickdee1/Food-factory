package foodchain.reporters;

import org.json.JSONObject;
import foodchain.products.Product;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ProductReporter implements Visitor {

    private List<Product> productList;

    public ProductReporter(List<Product> productList) {
        this.productList = productList;
    }

    public void generateForAll() {
        Map<String, List> outputMap = new LinkedHashMap<String, List>();
        String output_file = "products";

        List<Map> allProductsReport = generateMapsForAll();
        outputMap.put("products", allProductsReport);

        generateJSON(new JSONObject(outputMap), output_file);
    }


    protected List<Map> generateMapsForAll() {
        List<Map> arrayOfProducts = new ArrayList<Map>();

        for (Product p : productList) {
            Map productMap = generateMapReportForProduct(p);
            arrayOfProducts.add(productMap);
        }

        return arrayOfProducts;
    }


    protected Map<String, Object> generateMapReportForProduct(Product product) {
        String name = product.getName();
        String state = product.getState().getStateName();
        Integer price = product.getPrice();

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("name", name);
        map.put("current_state", state);
        map.put("price", price);

        return map;
    }

    private void generateJSON(JSONObject object, String name) {
        String filepath = "reports/" + name + ".json";

        try {
            FileWriter file = new FileWriter(new File(filepath));
            file.write(object.toString(2));
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
