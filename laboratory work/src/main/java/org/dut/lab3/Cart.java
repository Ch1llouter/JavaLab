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
