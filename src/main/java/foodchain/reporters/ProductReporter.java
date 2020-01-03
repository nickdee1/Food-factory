package foodchain.reporters;
import foodchain.products.Product;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Class for generating JSON report for the products in simulation
 */
public class ProductReporter implements Visitor {

    /**
     * List of products the report is generated for
     */
    private List<Product> productList;

    /**
     * Constructor for ProductReporter
     * @param productList the list with products report is generated for
     */
    public ProductReporter(List<Product> productList) {
        this.productList = productList;
    }


    ProductReporter() {
    }


    /**
     * Method generates JSON report for all products from product list {@link #productList}
     */
    public void generateForAll() {
        Map<String, List> outputMap = new LinkedHashMap<String, List>();
        String output_file = "products";

        List<Map> allProductsReport = generateMapsForAll();
        outputMap.put("products", allProductsReport);

        generateJSON(new JSONObject(outputMap), output_file);
    }


    /**
     *  Method generates List of Maps for the products in order to grab all its' attributes
     *  and further converting them into JSON format
     * @return generated List with necessary attributes
     * @throws NullPointerException in case {@link #productList} is null
     */
    List<Map> generateMapsForAll() throws NullPointerException {
        List<Map> arrayOfProducts = new ArrayList<Map>();

        for (Product p : productList) {
            Map productMap = generateMapReportForProduct(p);
            arrayOfProducts.add(productMap);
        }

        return arrayOfProducts;
    }

    public void generateReportForProduct(Product product) {
        String name = product.getName();
        Map outputMap = generateMapReportForProduct(product);
        generateJSON(new JSONObject(outputMap), name);
    }

    /**
     * Method generates Map for the product in order to grab all it's attributes
     * and further converting them into JSON format
     * @param product the party the Map is generated for
     * @return generated Map with necessary attributes
     */
    Map<String, Object> generateMapReportForProduct(Product product) {
        String name = product.getName();
        String state = product.getState().getStateName();
        Integer price = product.getPrice();
        List params = product.getAllParameters();

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("name", name);
        map.put("current_state", state);
        map.put("price", price);
        map.put("params", params);

        return map;
    }

    /**
     * Method generates JSON file for the report and saves it into
     * directory /reports/
     * @param object the JSONObject for which the report is generated for
     * @param name the filename without extension report would be saved with
     */
    private void generateJSON(JSONObject object, String name) {
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
