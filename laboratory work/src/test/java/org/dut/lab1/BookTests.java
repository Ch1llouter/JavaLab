package org.dut.lab1;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTests {

    private static Book book;

    @BeforeAll
    static void setUp()
    {
        book = new Book("name", "author", "1-46534-34-53", 2000);
    }

    @Test
    void Book_String_GetBookName(){
        assertEquals("name", book.GetBookName(), "Метод спрацював не правильно");
    }

    @Test
    void Book_String_GetBookAuthor(){
        assertEquals("author", book.GetAuthorName(), "Метод спрацював не правильно");
    }

    @Test
    void Book_String_GetBookNumber(){
        assertEquals("1-46534-34-53", book.GetBookNumberISBN(), "Метод спрацював не правильно");
    }

    @Test
    void Book_String_GetBookPublishingYear(){
        assertEquals("2000", book.GetBookPublishingYear(), "Метод спрацював не правильно");
    }
}
