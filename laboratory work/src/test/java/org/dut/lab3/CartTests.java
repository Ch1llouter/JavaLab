package org.dut.lab3;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
public class CartTests {
    Cart cart;
    @BeforeEach
    void setup(){
       cart = new Cart();
    }
    @Test
    void Cart_ArrayList_getStorageFromCart(){
        cart.addProductToCart(new Product(1, "Lamp", 12000));
        cart.addProductToCart(new Product(2, "Sofa", 22332));
        ArrayList<Product> storage = new ArrayList<>();
        storage.add(new Product(1, "Lamp", 12000));
        storage.add(new Product(2, "Sofa", 22332));
        assertEquals(storage, cart.getStorage());
    }
    @Test
    void Cart_void_addProductToCart(){
        int sizeStart = cart.getStorage().size();
        cart.addProductToCart(new Product(1, "Lamp", 12000));
        int sizeEnd = cart.getStorage().size();
        assertTrue(sizeStart < sizeEnd);
    }
    @Test
    void Cart_void_deleteProductFromCart(){
        Product product = new Product(1, "Lamp", 12000);
        cart.addProductToCart(product);
        cart.deleteProductFromCart(product);
        assertTrue(cart.getStorage().isEmpty());
    }
}
