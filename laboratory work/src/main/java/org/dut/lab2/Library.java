package org.dut.lab2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Library implements IManageable {
    private ArrayList<Item> items;
    private ArrayList<Patron> patrons;
    public Library(){
        items = new ArrayList<Item>();
        patrons = new ArrayList<Patron>();
    }
    public ArrayList<Item> getItems(){
        return items;
    }
    public ArrayList<Patron> getPatrons(){
        return patrons;
    }
    public void registerPatron(Patron patron) throws SecurityException{
        if(!(patron.getName() == null || patron.getID() == null)){
            if(!patrons.contains(patron)){
                patrons.add(patron);
            }
            else {
                throw new SecurityException("Patron already registered");
            }
        }
        else{
            throw new SecurityException("Patron has no full data");
        }
    }
    public void lendItem(Patron patron, Item item) throws SecurityException{
        if(listAvailable().contains(item) && items.contains(item)){
            if(patrons.contains(patron)){
                item.borrowItem();
                patron.borrow(item);
            }
            else{
                throw new SecurityException("The patron is not registered");
            }
        }
        else{
            throw new SecurityException("Item is not available");
        }
    }
    public void returnItem(Patron patron, Item item) throws SecurityException{
        if(item.getIsBorrowed() && items.contains(item) && patron.getItemsList().contains(item)){
            item.returnItem();
            patron.remove(item);
        }
        else{
            throw new SecurityException("Item has not been borrowed / doesn't exist");
        }
    }
    public void add(Item item){
        items.add(item);
    }
    public void remove(Item item) throws SecurityException {
        if(items.contains(item)){
            items.remove(item);
        }
        else{
            throw new SecurityException("Item is not exist");
        }
    }
    public ArrayList<Item> listAvailable(){
        ArrayList<Item> list = new ArrayList<Item>();
        for (Item item : items) {
            if(!item.isBorrowed){
                list.add(item);
            }
        }
        return list;
    }
    public HashMap<Patron, ArrayList<Item>> listBorrowed(){
        HashMap<Patron, ArrayList<Item>> patronsBooks = new HashMap<Patron, ArrayList<Item>>();
        for(Patron patron : patrons){
            patronsBooks.put(patron, patron.getItemsList());
        }
        return patronsBooks;
    }
}
