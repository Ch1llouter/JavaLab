package org.dut.lab3;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
public class ProductTests {
    static Product product;
    @BeforeAll
    static void setup(){
        product = new Product(134, "Something", 324233);
    }
    @Test
    void Product_int_ReturnsId(){
        assertEquals(134, product.id());
    }
    @Test
    void Product_String_ReturnsName(){
        assertEquals("Something", product.name());
    }
    @Test
    void Product_double_ReturnsPrice(){
        assertEquals(324233, product.price());
    }
}
