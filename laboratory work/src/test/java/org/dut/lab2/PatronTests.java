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
