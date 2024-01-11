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
