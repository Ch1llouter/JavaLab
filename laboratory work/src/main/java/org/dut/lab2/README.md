# Друга Лабораторна 
К-сть класів: 5, з них абстрактних: 1. <br/>
К-сть інтерфейсів: 1. <br/>
К-сть класів-тестів: 4.
---
Клас Book:
```java
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
```
Клас DVD:
```java
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
```
Абстрактний клас Item:
```java
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
```
Інтерфейс IManageable:
```java
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
```
Клас Library:
```java
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
```
Клас Patron:
```java
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
```
Клас-тест BookTests:
```java
package org.dut.lab2;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class BookTests {
    private static Book book;
    @BeforeAll
    static void setUp()
    {
        book = new Book("testTitle", "1242-3235-34534-42", false, "testAuthor");
    }

    @Test
    void Book_void_setBorrowedAsTrue(){
        book.borrowItem();
        assertTrue(book.getIsBorrowed(), "Метод виконався неправильно");
    }
    @Test
    void Book_void_setBorrowedAsFalse(){
        book.returnItem();
        assertFalse(book.getIsBorrowed(), "Метод виконався неправильно");
    }
}
```
Клас-тест DVDTests:
```java
package org.dut.lab2;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class DVDTests {
    private static DVD dvd;
    @BeforeAll
    static void setUp()
    {
        dvd = new DVD("testTitle", "234-543-12-4523-1", false, 2);
    }

    @Test
    void DVD_void_setBorrowedAsTrue(){
        dvd.borrowItem();
        assertTrue(dvd.getIsBorrowed(), "Метод виконався неправильно");
    }
    @Test
    void DVD_void_setBorrowedAsFalse(){
        dvd.returnItem();
        assertFalse(dvd.getIsBorrowed(), "Метод виконався неправильно");
    }
}
```
Клас-тест Library Tests:
```java
package org.dut.lab2;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTests {
    private static Library library;
    private static Patron patron;
    private static Book book;
    private static Book book1;

    @BeforeEach
    void setUp()
    {
        library = new Library();
        patron = new Patron("d", "23-43-43-43-4333");
        book = new Book("a", "243-43-4-34", false, "waefs");
        book1 = new Book("4", "343-43-", false, "ijjj");
        library.add(book);
        library.add(book1);
    }
    @Test
    void Library_void_registerPatron(){
        int patronsCountStart = library.getPatrons().size();
        library.registerPatron(patron);
        int patronsCountEnd = library.getPatrons().size();
        assertTrue(patronsCountStart < patronsCountEnd);

    }
    @Test
    void Library_void_setBorrowedAsTrue(){
        library.add(book1);
        library.lendItem(patron, book1);
        assertTrue(book1.getIsBorrowed());
    }
    @Test
    void Library_void_setBorrowedAsFalse(){
        book.isBorrowed = false;
        library.add(book);
        library.lendItem(patron, book);
        library.returnItem(patron, book);
        assertFalse(book.getIsBorrowed());
    }
    @Test
    void Library_void_addNewItemToLibrary(){
        int lengthInStart = library.getItems().size();
        library.add(book1);
        int lengthInEnd = library.getItems().size();
        assertTrue(lengthInStart < lengthInEnd);
    }
    @Test
    void Library_void_RemoveItemFromLibrary(){
        int lengthInStart = library.getItems().size();
        library.remove(book1);
        int lengthInEnd = library.getItems().size();
        assertTrue(lengthInStart > lengthInEnd);
    }

    @Test
    void Library_ArrayListOfItems_returnsArrayOfAvailableItems(){
        Library libraryTestVariable = new Library();
        libraryTestVariable.add(new DVD("sfeefs", "fesfs-fes-ef", false, 2));
        int itemsCount = libraryTestVariable.getItems().size();
        ArrayList<Item> availableItems= libraryTestVariable.listAvailable();
        assertTrue(availableItems.size() == itemsCount);
    }
    @Test
    void Library_HashMap_returnsHAshMapWithPatronsAndItemsTheyBorrowed(){
        HashMap<Patron, ArrayList<Item>> hashMap = new HashMap<Patron, ArrayList<Item>>();
        library.registerPatron(patron);
        library.lendItem(patron, book);
        library.lendItem(patron, book1);
        hashMap.put(patron, library.getItems());
        assertTrue(hashMap.equals(library.listBorrowed()));
    }
}


```
Клас-тест PatronTests:
```java
package org.dut.lab2;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PatronTests {
    static Patron patron;
    @BeforeAll
    static void setUp()
    {
        patron = new Patron("TestName", "342-234-234-324");
    }

    @Test
    void Patron_String_GetName(){
        assertEquals("TestName", patron.getName());
    }
    @Test
    void Patron_String_GetID(){
        assertEquals("342-234-234-324", patron.getID());
    }
    @Test
    void Patron_String_GetItemListOfBorrowedItems(){
        DVD dvd = new DVD("fsf", "sfsef", true, 3);
        patron.borrow(dvd);
        assertTrue(patron.getItemsList().contains(dvd));
    }
    @Test
    void Patron_void_addItemInList(){
        int sizeOfBorrowThingsStart = patron.getItemsList().size();
        DVD dvd = new DVD("fsf", "sfsef", true, 3);
        patron.borrow(dvd);
        int sizeOfBorrowThingsEnd = patron.getItemsList().size();
        assertTrue(sizeOfBorrowThingsStart < sizeOfBorrowThingsEnd);
    }
    @Test
    void Patron_void_removeItemFromList(){
        DVD dvd = new DVD("fsf", "sfsef", true, 3);
        patron.borrow(dvd);
        int sizeOfBorrowThingsStart = patron.getItemsList().size();
        patron.remove(dvd);
        int sizeOfBorrowThingsEnd = patron.getItemsList().size();
        assertTrue(sizeOfBorrowThingsStart > sizeOfBorrowThingsEnd);
    }
}
```

---
Усі тести пройшли успішне випробовування <br/>
Степанов Андрій, ПД-33, 24.10.2023