///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:              Catalog
// Files:              Catalog.java
// Quarter:            CSE11 SPRING 2025
//
// Author:             DEONN ALMIROL
// Email:              DALMIROL@UCSD.EDU
// Instructor's Name:  BEN OCHOA
//
///////////////////////////////////////////////////////////////////////////////
//                   STUDENTS WHO GET HELP COMPLETE THIS SECTION
//                   You must fully acknowledge and credit sources of help.
//                   Instructors and TAs do not have to be credited here,
//                   but roommates, relatives, strangers, etc do.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   Avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of
//                   of any information you find.
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * creates a virtual catalog containing an array of books
 * 
 * BUGS: removeBook functionality
 * 
 * @author deonn almirol
 */
public class Catalog {
    private int capacity;
    private int size;
    private Book[] catalog;

    private static final int INITIAL_CAPACITY = 10;
    private static final int RESIZE_FACTOR = 2;
    private static final int NOT_FOUND = -1;

    /**
     * no arg constructor, sets capacity to initial cap const
     * as well as everything to zero
     */
    public Catalog() {

        this.capacity = INITIAL_CAPACITY;
        this.size = 0;
        this.catalog = new Book[INITIAL_CAPACITY];

    }

    /**
     * arg constructor that dictates initial capacity
     * 
     * @param initialCapacity the initial capacity of the catalog
     */
    public Catalog(int initialCapacity) {

        this.capacity = initialCapacity;
        this.size = 0;
        this.catalog =  new Book[initialCapacity];

    }

    /**
     * checks if there is enough space for new books, if there isnt,
     * resize catalog by resize factor and return new book array
     * 
     */
    private void ensureCapacity() {
        if (this.size >= this.capacity) {
            this.capacity += RESIZE_FACTOR;
            Book[] newCatalog = new Book[this.capacity];

            for (int i = 0; i < this.catalog.length; i++) {
                newCatalog[i] = this.catalog[i];
            }

            this.catalog = newCatalog;
        }
    }

    /**
     * returns index of book found through isbn
     * 
     * @param isbn isbn of book to search
     * @return index of book
     */
    private int findBookIndexByIsbn(long isbn) { 
        
        for (int i = 0; i < this.size; i++) {
            if (this.catalog[i].getIsbn() == isbn) {
                return i;
            }
        }
        
        return NOT_FOUND; 
    }

    /**
     * adds provided book to catalog
     * 
     * @param newBook book to add to catalog
     */
    public void addBook(Book newBook) {
        // check if book already exists
        for (int i = 0; i < this.catalog.length; i++) {
            if (this.catalog[i] == null) {
                continue;
            }

            if (this.catalog[i].getIsbn() == newBook.getIsbn()) {
                return;
            }
        }

        ensureCapacity();

        //determine correct index
        int correct_index = 0;
        for (int i = 0; i < this.catalog.length; i++) {
            if (this.catalog[i] == null) {
                continue;
            }

            if (this.catalog[i].getIsbn() >= newBook.getIsbn()) {
                correct_index = i;
            }
        }

        //shift items one right
        for (int i = this.catalog.length-1; i >= correct_index; i--) {
            if (i == this.catalog.length-1) {
                continue;
            }

            this.catalog[i+1] = this.catalog[i];
        }

        this.catalog[correct_index] = newBook;
        this.size++;

    }

    /**
     * removes provided book from catalog
     * 
     * @param bookToRemove book to remove from catalog
     */
    public void removeBook(Book bookToRemove) {
        // check if book already exists
        for (int i = 0; i < this.catalog.length; i++) {
            if (this.catalog[i] == null) {
                continue;
            }

            if (this.catalog[i].getIsbn() == bookToRemove.getIsbn()) {
                break;
            }
        }

        // get index
        int index = 0;
        for (int i = 0; i < this.catalog.length; i++) {
            if (this.catalog[i] == null) {
                continue;
            }

            if (this.catalog[i].getIsbn() == bookToRemove.getIsbn()) {
                index = i;
                break;
            }
        }

        //shift items to left and replace index in catalog
        boolean alreadyRemoved = false;
        for (int i = index; i < this.catalog.length; i++) {
            if (this.catalog[i] == null) {
                continue;
            }

            if (i+1 >= this.catalog.length) {
                this.catalog[i] = null;
                break;
            }

            if (index == 0 && alreadyRemoved == false) {
                this.catalog[i] = null;
                this.size--;
                alreadyRemoved = true;
            }

            if (i == 0) {
                continue;
            }

            this.catalog[i-1] = this.catalog[i];
            this.catalog[i] = null;
        }

        if (alreadyRemoved == false) {
            this.size--;
        }
    }

    /**
     * returns book that matches given isbn
     * 
     * @param isbn isbn to use
     * @return the resulting book
     */
    public Book returnBookByISBN(long isbn) {
        for (int i = 0; i < this.catalog.length; i++) {
            if (this.catalog[i] == null) {
                continue;
            }

            if (this.catalog[i].getIsbn() == isbn) {
                return this.catalog[i];
            }
        }

        return null;
    }

    /**
     * integer getter, gets size of catalog
     * 
     * @return size of catalog
     */
    public int getSize() {
        return this.size;
    }

    /**
     * integer getter, gets capacity of catalog
     * 
     * @return capacity of catalog
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * returns book array of this.size
     * 
     * @return array of all books in catalog
     */
    public Book[] getAllBooks() {
        int count = 0;

        for (int i = 0; i < this.catalog.length; i++) {
            if (this.catalog[i] != null) {
                count++;
            }
        }

        Book[] bookList = new Book[count];

        for (int i = 0; i < count; i++) {
            bookList[i] = this.catalog[i];
        }

        return bookList;
    }

    /**
     * runs unit tests
     * 
     * @return if all tests pass
     */
    @SuppressWarnings("checkstyle:MagicNumber") // DO NOT CHANGE THIS LINE!!!
    public static boolean unitTests() {
        boolean passed = true;

        // Test case 1
        Catalog cat1 = new Catalog();
        Book bookToAdd = new Book("Title2", "Author2", 9876543210987L);
        cat1.addBook(bookToAdd);
        System.out.println(String.format("cat1 new size: %d", cat1.getSize()));
        if (cat1.getSize() != 1) {
            System.out.println("Catalog Test 1 FAILED: Add book size check");
            passed = false;
        }

        //Test case 2
        Catalog cat2 = new Catalog();
        Book book1 = new Book("Balh", "Bahsd", 1987l);
        Book book2 = new Book("asdasd", "asddd", 2049l);
        Book book3 = new Book("kill jon", "bob", 1l);
        cat2.addBook(book1);
        cat2.addBook(book2);
        cat2.addBook(book3);
        cat2.removeBook(book1);
        cat2.removeBook(book2);
        cat2.removeBook(book3);
        System.out.println(String.format("cat2 new size: %d", cat2.getSize()));
        if (cat2.getSize() != 0) {
            System.out.println("Catalog Test 2 FAILED: " + 
                "Adding and removing 3 books size check");
            passed = false;
        }

        //Test case 3
        Catalog cat3 = new Catalog();
        Book book4 = new Book("Balh", "Bahsd", 1987l);
        Book book5 = new Book("asdasd", "asddd", 2049l);
        Book book6 = new Book("kill jon", "bob", 1l);
        Book book7 = new Book("title lol", "Jerry sppringler", 8891);
        Book book8 = new Book("HEELLLPPP", "SOS", 666);
        cat3.addBook(book4);
        cat3.addBook(book5);
        cat3.addBook(book6);
        cat3.addBook(book7);
        cat3.addBook(book8);
        cat3.removeBook(book4);
        cat3.removeBook(book5);
        cat3.removeBook(book6);
        System.out.println(String.format("cat3 new size: %d", cat3.getSize()));
        if (cat3.getSize() != 2) {
            System.out.println("Catalog Test 3 FAILED: " + 
                "Adding 5 and removing 3 books size check");
            passed = false;
        }

        //Test case 4
        Catalog cat4 = new Catalog();
        cat4.addBook(book1);
        cat4.addBook(book2);
        cat4.addBook(book3);
        cat4.removeBook(book2);
        System.out.println(String.format("cat4 new size: %d", cat4.getSize()));
        if (cat4.getSize() != 2) {
            System.out.println("Catalog Test 4 FAILED: " + 
                "Adding 3 and removing 1 books size check");
            passed = false;
        }

        return passed;
    }

    /**
     * main entry point of Catalog.java
     * 
     * @param args command line args
     */
    public static void main(String[] args) {
        if (unitTests()) {
            System.out.println("Catalog: All unit tests passed.\n");
        } else {
            System.out.println("Catalog: ERROR: Failed test.\n");
            return;
        }
    }
}