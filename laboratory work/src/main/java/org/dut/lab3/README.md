# Лабараторна робота №3
**Кількість класів: 4** <br />
**Кількість тестових класів: 3** <br />
**Кількість enum: 1**
---
## Клас Cart:
к-сть методів: 2 <br />
к-сть get-методів: 1 <br />
к-сть полів: 1 <br />
к-сть конструкторів: 1
```java
package org.dut.lab3;

import java.util.ArrayList;

public class Cart{
    ArrayList<Product> storage;
    public Cart(){
        storage = new ArrayList<Product>();
    }
    public ArrayList<Product> getStorage(){
        return storage;
    }
    public void addProductToCart(Product product){
        storage.add(product);
    }
    public void deleteProductFromCart(Product product) {
        storage.remove(product);
    }
}
```
---
## Клас Order:
к-сть методів: 1 <br />
к-сть get-методів: 1 <br />
к-сть полів: 3 <br />
к-сть конструкторів: 1
```java
package org.dut.lab3;

import java.util.ArrayList;

public class Order{
    ArrayList<Product> storage;
    int orderId;
    Status status;
    public Order(ArrayList<Product> storage, int orderId){
        this.storage = storage;
        this.orderId = orderId;
        status = Status.IS_NOT_PAID;
    }
    public Status getStatus(){
        return status;
    }
    public void payMyOrder(){
        status = Status.IS_PAID;
    }
}
```
---
## Клас(record) Product
к-сть методів: 0 <br />
к-сть get-методів: 3 <br />
к-сть полів: 3 <br />
к-сть конструкторів: 1
```java
package org.dut.lab3;

import java.math.BigDecimal;

public record Product(int id, String name, double price) {

}
```
---
## Enum Status
к-сть перечислень: 2
```java
package org.dut.lab3;

public enum Status {
    IS_NOT_PAID,
    IS_PAID
}
```
---
## Тестовий клас CartTests
```java
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
```
---
## Тестовий клас OrderTests
```java
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
```
---
## Тестовий клас ProductTests
```java
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
```
---
*Усі тести пройшли перевірки успішно, try catch блоки пропущені спеціально для економії часу* <br />
Степанов Андрій, ПД-33