package org.dut.lab2;

public abstract class Item {
    private String title;
    private String uniqueID;
    protected Boolean isBorrowed = false;

    public Item(String title, String uniqueID){
        this.title = title;
        this.uniqueID = uniqueID;
    }
    public String getTitle(){
        return title;
    }
    public String getUniqueID(){
        return uniqueID;
    }
    public Boolean getIsBorrowed(){
        return isBorrowed;
    }
    public abstract void borrowItem();
    public abstract void returnItem();
}
