package org.dut.lab1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import static java.lang.System.out;

public class Library {
    private final ArrayList<Book> bookShelf;
    public Library(){
        bookShelf = new ArrayList<>();
    }
    public final boolean addBook(Book bookForAdding)
    {
        bookShelf.add(bookForAdding);
        out.println("----книга успішно додана----");
        return true;
    }
    public final boolean addBook(Collection<Book> books)
    {
        bookShelf.addAll(books);
        out.println("----книги успішно додані----");
        return true;
    }

    public final boolean addBook()
    {
        String newBookName;
        String newBookAuthor;
        String newBookNumberISBN;
        Integer newBookPublishingYear;

        newBookName = inputInfo("введіть назву книжки: ");
        newBookAuthor = inputInfo("введіть автор(а/ів) книжки: ");
        newBookNumberISBN = inputInfo("введіть номер ISBN книжки: ");
        do
        {
            try
            {
                newBookPublishingYear = Integer.parseInt(inputInfo("введіть рік видання: "));
                bookShelf.add(new Book(newBookName, newBookAuthor, newBookNumberISBN, newBookPublishingYear));
                out.println("----книга успішно додана----");
                break;

            }
            catch (NumberFormatException exception)
            {
                out.println("Помилка: ви ввели не число.");
            }
        } while(true);
        return true;
    }
    public final boolean getInfoAboutAllBooks()
    {
        if(!bookShelf.isEmpty()){
            for(Book book : bookShelf){
                getBookInfo(book);
            }
        }
        else{
            out.println("----сховище пусте----");
        }
        return true;
    }
    public final boolean searchBook()
    {
        boolean bookIsFound = false;
        for (Book book : bookShelf) {
            if(inputInfo("Введіть ISBN номер книжки для пошуку: ").equals(book.GetBookName())){
                getBookInfo(book);
                bookIsFound = true;
                return bookIsFound;
            }
        }
        out.println("----книжка відсутня----");
        return true;
    }
    public final boolean searchBook(String bookName)
    {
        boolean bookIsFound = false;
        for (Book book : bookShelf) {
            if(bookName.equals(book.GetBookName())){
                getBookInfo(book);
                bookIsFound = true;
                return bookIsFound;
            }
        }
        out.println("----книжка відсутня----");
        return true;
    }
    private void getBookInfo(Book book)
    {
        out.println(book.GetBookName() + ":");
        out.println("\t автор: " + book.GetAuthorName());
        out.println("\t номер ISBN: " + book.GetBookNumberISBN());
        out.print("\t рік видання: ");
        if(book.GetBookPublishingYear() != null){
            out.println(book.GetBookPublishingYear());
        }
        else {
            out.println("----інформація відсутня----");
        }
    }
    public final boolean moveBookToTrash()
    {
        deleteBook(inputInfo("Введіть ISBN номер книжки для пошуку: "));
        return true;
    }
    public final boolean moveBookToTrash(String numberISBN)
    {
        deleteBook(numberISBN);
        return true;
    }
    private String inputInfo(String message)
    {
        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();
        if(value == null){
            inputInfo(message);
        }
        return value;
    }
    private boolean deleteBook(String numberISBN)
    {
        boolean isDeleted = false;

        for (int i = 0; i < bookShelf.size(); i++) {
            if(bookShelf.get(i).GetBookNumberISBN().equals(numberISBN)){
                bookShelf.remove(i);
                isDeleted = true;
                out.println("----книжка була успішно видалена----");
                return isDeleted;
            }
        }
        out.println("----книжки немає в бібліотеці----");
        return true;
    }
}
