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