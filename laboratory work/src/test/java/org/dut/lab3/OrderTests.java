package org.dut.lab3;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
public class OrderTests {
    Order order;
    ArrayList<Product> products;
    @BeforeEach
    void setup(){
        products = new ArrayList<Product>();
        products.add(new Product(12, "efs", 23213));
        products.add(new Product(13, "sdfsefsfes", 23213));
        products.add(new Product(14, "sefsefsef", 23213));
        order = new Order(products, 23);
    }
    @Test
    void Order_Status_GetOrderStatus() {
        assertSame(order.getStatus(), Status.IS_NOT_PAID);
    }
    @Test
    void Order_Status_setOrderStatusAsPaid() {
        order.payMyOrder();
        assertSame(order.getStatus(), Status.IS_PAID);
    }
}
