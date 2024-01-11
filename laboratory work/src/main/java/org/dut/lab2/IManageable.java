package org.dut.lab2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface IManageable {
    public ArrayList<Item> listAvailable();
    public HashMap<Patron, ArrayList<Item>> listBorrowed();
    public void add(Item item);
    public void remove(Item item) throws Exception;
}
