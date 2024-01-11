package org.dut.lab2;

public class Book extends Item {
    private String author;
    public Book(String title, String uniqueID, Boolean isBorrowed, String author) {
        super(title, uniqueID);
        this.author = author;
    }
    public void borrowItem(){
        isBorrowed = true;
    }
    public void returnItem(){
        isBorrowed = false;
    }
}
