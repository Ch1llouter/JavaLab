package org.dut.lab2;

public class DVD extends Item{
    private int duration;
    public DVD(String title, String uniqueID, Boolean isBorrowed, int duration){
        super(title, uniqueID);
        this.duration = duration;
    }
    public void borrowItem(){
        isBorrowed = true;
    }
    public void returnItem(){
        isBorrowed = false;
    }
}
