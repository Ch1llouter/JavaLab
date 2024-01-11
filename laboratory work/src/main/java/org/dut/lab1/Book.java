package org.dut.lab1;

import java.time.LocalDate;

public class Book {
    private String name;
    private String author;
    private String numberISBN;
    private Integer publishingYear;

    public Book(String name, String author, String numberISBN, int publishingYear)
    {
        this.name = name;
        this.author = author;
        this.numberISBN = numberISBN;
        this.publishingYear = publishingYear;
    }
    public String GetBookName()
    {
        if(name == null){
            name = "Інформація відсутня";
        }
        return name;
    }
    public String GetAuthorName()
    {
        if(author == null){
            author = "Інформація відсутня";
        }
        return author;
    }
    public String GetBookNumberISBN()
    {
        if(numberISBN == null){
            numberISBN = "Інформація відсутня";
        }
        return numberISBN;
    }
    public Integer GetBookPublishingYear()
    {
        LocalDate date = LocalDate.now();
        if(publishingYear > date.getYear()){
            publishingYear = null;
        }
        return publishingYear;
    }
}
