package org.dut.lab1;

import org.junit.jupiter.api.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class LibraryTests {

    private static Library library;

    @BeforeAll
    static void setUp()
    {
        library = new Library();
    }

    @Test
    void Library_Void_ShouldAddNewBookWithArguments()
    {
        Book book = new Book("Some name", "some author", "1-456-57-3456", 2021);
        assertTrue(library.addBook(book), "Метод addBook спрацював не правильно");
    }

    @Test
    void Library_Void_ShouldAddNewBookWithCollections()
    {
        ArrayList<Book> arraybook = new ArrayList<>();
        arraybook.add(new Book("name", "author", "2423422343242", 2034));
        assertTrue(library.addBook(arraybook), "Метод addBook спрацював правильно");
    }

    @Test
    void Library_Void_GetInfoAboutAllBooks(){
        assertTrue(library.getInfoAboutAllBooks(), "Метод getInfoAboutAllBook спрацював неправильно");
    }

    @Test
    void Library_Void_MoveToTrashTheBookWithAnArgument(){
        assertTrue(library.moveBookToTrash("1-342-234-653-23"), "Метод searchBook спрацював неправильно");
    }

    @Test
    void Library_Void_FindBookWithArguments(){
        assertTrue(library.searchBook("book name"), "Метод searchBook спрацював неправильно");
    }
}
