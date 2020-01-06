package foodchain.reporters;

import foodchain.products.Milk;
import foodchain.products.Pork;
import foodchain.products.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductReporterTest {

    private Product milk, pork;

    @BeforeEach
    void setup() {
        milk = new Milk();
        pork = new Pork();
    }

    @Test
    void generateForAll() {
        List<Product> products = new ArrayList<Product>();
        products.add(milk);
        products.add(pork);
        ProductReporter pr = new ProductReporter(products);
        pr.generateForAll();

        File file = new File("reports/products");
        assertNotNull(file);
    }
}