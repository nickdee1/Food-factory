package foodchain;

import foodchain.products.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodFactoryTest {

    @Test
    void makeProduct() {
        Product milk = FoodFactory.makeProduct("milk");
        Product apple = FoodFactory.makeProduct("apple");
        Product pork = FoodFactory.makeProduct("pork");

        assertNotNull(milk);
        assertNotNull(apple);
        assertNotNull(pork);
        assertEquals(milk.getName(), "Milk");
        assertEquals(apple.getName(), "Apple");
        assertEquals(pork.getName(), "Pork");
    }
}