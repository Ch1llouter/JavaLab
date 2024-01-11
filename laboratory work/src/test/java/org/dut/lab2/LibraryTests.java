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