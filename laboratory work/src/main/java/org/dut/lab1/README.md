<h1 align="center">Лабораторна робота №1</h1>

***

**Навігація по файлу:**

>   * <a href="#clases">Класи</a>
>    * [Клас Library](#клас-library)
>    * [Клас Book](#клас-book)
>  * <a href="#clases-tests">Класи-тести</a>
>      * [LibraryTests](#librarytests-)
>      * [BookTests](#booktests)


***

**Кількість класів: 2**

> - Library
> - Book

**Кількість тестових класів: 2**

> - LibraryTests
> - BookTests

***

## <h2 align="center" id="clases">Класи</h2>

### Клас Library

**Кількість полів: 1**

```java
 // Створюється змінна для збереження array масиву в якому збегіратимуться книжки
private final ArrayList<Book> bookShelf;
```

**Кількість конструкторів: 1**
```java
// Елементарний конструктор який ініціалізує масив для роботи
public Library()
{
    bookShelf = new ArrayList<>();
}
```

**Кількість методів: 11**

> Приватних: 3
> 
> Публічних: 8

```java
// Одна з перегрузок методу addBook. Додає надану у аргументи книжку в масив
public final boolean addBook(Book bookForAdding)
{
    bookShelf.add(bookForAdding);
    out.println("----книга успішно додана----");
    return true;
}
```
```java
// Одна з перегрузок методу addBook. Додає наданий у аргументи цілий набір книжок в масив
public final boolean addBook(Collection<Book> books)
{
    bookShelf.addAll(books);
    out.println("----книги успішно додані----");
    return true;
}
```
```java
// Одна з перегрузок методу addBook. Додає введену вручну книжку в масив
public final boolean addBook()
{
    String newBookName;
    String newBookAuthor;
    String newBookNumberISBN;
    Integer newBookPublishingYear;

    newBookName=inputInfo("введіть назву книжки: ");
    newBookAuthor=inputInfo("введіть автор(а/ів) книжки: ");
    newBookNumberISBN=inputInfo("введіть номер ISBN книжки: ");
    do
    {
        try
        {
            newBookPublishingYear=Integer.parseInt(inputInfo("введіть рік видання: "));
            bookShelf.add(new Book(newBookName,newBookAuthor,newBookNumberISBN,newBookPublishingYear));
            out.println("----книга успішно додана----");
            break;
        }
        catch(NumberFormatException exception)
        {
            out.println("Помилка: ви ввели не число.");
        }
    } while(true);
    return true;
}
```
```java
// Метод отримує інформацію про кожну книжку в нашому масиві (умовній полиці)
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
```
```java
// Одна з перегрузок методу який шукає потрібну нам книжку по введенному користувачем номеру ISBN і виводить про неї інформацію
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
```
```java
// Одна з перегрузок методу який шукає потрібну нам книжку по наданому в аргументи номеру ISBN і виводить про неї інформацію
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
```
```java
// Інформація наданої книжки через аргументи виводиться у консоль
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
```
```java
//Одна з перегрузок публічного методу що дозволяє видалити книгу через ввід даних с консолі
public final void moveBookToTrash()
{
    deleteBook(inputInfo("Введіть ISBN номер книжки для пошуку: "));
}
```
```java
// Одна з перегрузок публічного методу який дозволяє видалити потрібну книгу за наданим у аргументи числом ISBN
public final void moveBookToTrash(String numberISBN)
{
    deleteBook(numberISBN);
}
```
```java
// Приватний метод призначений для виділення багатоповторюючого коду в окремий метод. Метод дозволяє ввести дані з консолі
private String inputInfo(String message)
{
    Scanner scanner = new Scanner(System.in);
    String value = scanner.nextLine();
    if(value == null){
        inputInfo(message);
    }
    return value;
}
```
```java
// Метод, який працює під копотом у методів moveBookToTrash. Саме він видаляє книжку.
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
```
> ПРИМІТКА: [ПОВЕРНУТИСЬ ДО НАВІГАЦІЇ]()

***

### Клас Book
**Кількість полів: 4** ( усі поля приватні задля безпеки і неможливості напряму змінити дані )
```java
// Зберігає інформацію про назву книжки
private String name;
```
```java
// Зберігає інформацію про автора книжки
private String author;
```
```java
// Зберігає інформацію про серійний номер книжки
private String numberISBN;
```
```java
// Зберігає інформацію про рік другу книжки
private Integer publishingYear;
```
**Кількість конструкторів: 1**
```java
// В аргументи користувач надає дані - вони присвоюються полям об'єкту через оператор this
public Book(String name, String author, String numberISBN, int publishingYear)
{
    this.name = name;
    this.author = author;
    this.numberISBN = numberISBN;
    this.publishingYear = publishingYear;
}
```
**Кількість властивостей з get: 4**
```java
// Властивість, налаштована тільки на збір інформації про назву книжки
public String GetBookName()
{
    if(name == null){
        name = "Інформація відсутня";
    }
    return name;
}
```
```java
// Властивість налаштована тільки на збір інформації щодо автора книжки
public String GetAuthorName()
{
    if(author == null){
        author = "Інформація відсутня";
    }
    return author;
}
```
```java
// Властивість налаштована тільки на збір інформації щодо серійного номеру книжки
public String GetBookNumberISBN()
{
    if(numberISBN == null){
        numberISBN = "Інформація відсутня";
    }
    return numberISBN;
}
```
```java
// Властивість налаштована тільки на збір інформації щодо номеру публікації цієї книжки
public Integer GetBookPublishingYear()
{
    LocalDate date = LocalDate.now();
    if(publishingYear > date.getYear()){
        publishingYear = null;
    }
    return publishingYear;
}
```
> ПРИМІТКА: [ПОВЕРНУТИСЬ ДО НАВІГАЦІЇ]()
***

<h2 align="center" id="clases-tests">Класи - тести</h2>
### LibraryTests 
*(Тестується клас Library)*

**Кількість полів: 1** 
```java
// В полі зберігається масив в якому будуть зберігатись книжки
private static Library library;
```
**Кількість методів: 1**
```java
// Статичний метод налаштувань й ініціалізацій. "Тег" BeforeAll вказує на те, що цей метод викликається один єдиний раз для всіх тестів. Сам метод ініціалізує поле з масивом
@BeforeAll
static void setUp()
{
    library = new Library();
}
```
**Кількість тестів: 5**
```java
// Тест, який перевіряє на працездатність метод додавання нової книжки за допомогою аргумента
@Test
void Library_Void_ShouldAddNewBookWithArguments()
{
    Book book = new Book("Some name", "some author", "1-456-57-3456", 2021);
    assertTrue(library.addBook(book), "Метод addBook спрацював не правильно");
}
```
```java
// Тест, який перевіряє на працездатність метод додавання нової книжки через задавання в аргументи перелік книжок
@Test
void Library_Void_ShouldAddNewBookWithCollections()
{
    ArrayList<Book> arraybook = new ArrayList<>();
    arraybook.add(new Book("name", "author", "2423422343242", 2034));
    assertTrue(library.addBook(arraybook), "Метод addBook спрацював правильно");
}
```
```java
// Тест, який перевіряє на працездатність метод отримання інформації про всі присутні у сховищі книжки
@Test
void Library_Void_GetInfoAboutAllBooks(){
    assertTrue(library.getInfoAboutAllBooks(), "Метод getInfoAboutAllBook спрацював неправильно");
}
```
```java
// Тест, який перевіряє на працездатність метод знаходження книжки через передавання його назви в аргументи
@Test
void Library_Void_FindBookWithArguments(){
    assertTrue(library.searchBook("book name"), "Метод searchBook спрацював неправильно");
}
```
```java
// Тест, який перевіряє на працездатність метод видалення певної книжки за номером ISBN з масиву
@Test
void Library_Void_MoveToTrashTheBookWithAnArgument(){
    assertTrue(library.moveBookToTrash("1-342-234-653-23"), "Метод searchBook спрацював неправильно");
}
```
> ПРИМІТКА: [ПОВЕРНУТИСЬ ДО НАВІГАЦІЇ]()
***

### BookTests
*(Тестується клас Book)*

**Кількість полів: 1**
```java
// Це приватне поле зберігатиме об'єкт книги
private static Book book;
```

**Кількість методів: 1**
```java
// Цей метод дозволяє ініціалізувати поле book один раз до визову всіх тестів
@BeforeAll
static void setUp()
{
    book = new Book("name", "author", "1-46534-34-53", 2000);
}
```
**Кількість тестів: 4**
```java
// Цей тест перевіряє те, наскільки правильно повертається значення назви книги
@Test
void Book_String_GetBookName(){
    assertEquals("name", book.GetBookName(), "Метод спрацював не правильно");
}
```
```java
// Цей тест перевіряє те, наскільки правильно повертається значення авторів книги
@Test
void Book_String_GetBookAuthor(){
    assertEquals("author", book.GetAuthorName(), "Метод спрацював не правильно");
}
```
```java
// Цей тест перевіряє те, наскільки правильно повертається номер ISBN книги
@Test
void Book_String_GetBookNumber(){
    assertEquals("1-46534-34-53", book.GetBookNumberISBN(), "Метод спрацював не правильно");
}
```
```java
// Цей тест перевіряє те, наскільки правильно повертається рік публікації книги
@Test
void Book_String_GetBookPublishingYear(){
    assertEquals("2000", book.GetBookPublishingYear(), "Метод спрацював не правильно");
}
```
> ПРИМІТКА: [ПОВЕРНУТИСЬ ДО НАВІГАЦІЇ]()
***
<style>
    #footer{
        display: flex;
        justify-content: space-between;
    }
    #fullname, #date, #group{
        color: #C0C0C0;
        font-size: 10px;
        margin: 0px, 0px, 10px;
    }
</style>
<div id="footer">
    <p id="fullname"><i>Stepanov Andrii</i></p>
    <p id="date"><i>30.09.2023</i></p>
    <p id="group"><i>PD-33</i></p>
</div>