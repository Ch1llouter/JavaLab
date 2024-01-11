package org.dut.lab2;

import java.util.ArrayList;

public class Patron {
    private String name;
    private String ID;
    private ArrayList<Item> borrowedItems;

    public Patron(String name, String ID){
        this.name = name;
        this.ID = ID;
        borrowedItems = new ArrayList<Item>();
    }
    public String getName(){
        return name;
    }
    public String getID(){
        return ID;
    }
    public ArrayList<Item> getItemsList(){
        return borrowedItems;
    }
    public void borrow(Item item){
        borrowedItems.add(item);
    }
    public void remove(Item item){
        borrowedItems.remove(item);
    }
}