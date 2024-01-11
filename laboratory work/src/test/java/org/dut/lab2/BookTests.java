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
