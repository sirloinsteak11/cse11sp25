///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:              LibraryManager
// Files:              LibraryManager.java
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
 * creates a virtual library with a name and a catalog
 * 
 * BUGS: findAvailableBooksByAuthor functionality
 * 
 * @author deonn almirol
 */
public class LibraryManager {

    private String libraryName;
    private Catalog catalog;
    private static final String DEFAULT_NAME = "Geisel";

    /**
     * no arg constructor, sets library name to default and initializes
     * catalog
     */
    public LibraryManager() {
        this.libraryName = DEFAULT_NAME;
        this.catalog = new Catalog();
    }

    /**
     * arg constructor, sets library name to provided name and
     * initial catalog capacity
     * 
     * @param libraryName provided name of library
     * @param initialCapacity provided initial capacity
     */
    public LibraryManager(String libraryName, int initialCapacity) {
        this.libraryName = libraryName;
        this.catalog = new Catalog(initialCapacity);
    }

    /**
     * adds book to librarys catalog
     * 
     * @param newLibraryBook book to add
     */
    public void addBookToLibrary(Book newLibraryBook) {
        this.catalog.addBook(newLibraryBook);
    }

    /**
     * changes checked out status and sets borrower id
     * 
     * @param isbn isbn to search for book
     * @param borrowerID id of borrower
     * @return if the process is successful
     */
    public boolean checkOutBook(long isbn, String borrowerID) {
        Book requestedBook = this.catalog.returnBookByISBN(isbn);

        if (requestedBook.isCheckedOut() == true) {
            return false;
        }

        requestedBook.setIsCheckedOut(true);
        requestedBook.setBorrowerID(borrowerID);

        return true;
    }

    /**
     * checks in a checked out book
     * 
     * @param isbn isbn of book to search for
     * @return if the process was successful
     */
    public boolean checkInBook(long isbn) {
        Book givenBook = this.catalog.returnBookByISBN(isbn);

        if (givenBook.isCheckedOut() == false) {
            return false;
        }

        givenBook.setIsCheckedOut(false);
        givenBook.setBorrowerID(null);

        return true;
    }

    /**
     * takes author name and returns an array of all books\
     * matching that author
     * 
     * @param author name of author to search
     * @return the array of books written by the author
     */
    public Book[] findAvailableBooksByAuthor(String author) {
        Book[] catalog = this.catalog.getAllBooks();
        Book[] temp = new Book[this.catalog.getSize()];
        int count = 0;

        int resultIndex = 0;

        if (catalog.length == 0) {
            return new Book[0];
        }

        for (int i = 0; i < catalog.length; i++) {
            if(catalog[i] == null) {
                continue;
            }

            if(catalog[i].getAuthor() == author && 
                catalog[i].isCheckedOut() == false) {
                    
                temp[resultIndex] = catalog[i];
                resultIndex++;
                count++;
            }
        }

        Book[] result = new Book[count];

        for (int i = 0; i < count; i++) {
            result[i] = temp[i];
        }

        return result;
    }

    /**
     * returns integer count of checked out books
     * 
     * @return count of checked out books
     */
    public int getCheckedOutCount() {
        Book[] catalog = this.catalog.getAllBooks();
        int checkedOutCount = 0;

        for(int i = 0; i < catalog.length; i++) {
            if (catalog[i] == null) {
                continue;
            }

            if (catalog[i].isCheckedOut() == true) {
                checkedOutCount++;
            }
        }

        return checkedOutCount;
    }

    /**
     * runs unit tests
     * @return if all tests pass 
     */
    @SuppressWarnings("checkstyle:MagicNumber") // DO NOT CHANGE THIS LINE!!!
    public static boolean unitTests() {
        boolean passed = true;

        // Test case 1
        LibraryManager lm1 = new LibraryManager();
        Book bookToAdd = new Book("Title3", "Author3", 1122334455667L);
        lm1.addBookToLibrary(bookToAdd);
        if (lm1.getCheckedOutCount() != 0) {
            System.out.println("LibraryManager Test 1 FAILED: " + 
                "Add book checked out count");
            passed = false;
        }

        // Test case 2
        LibraryManager lm2 = new LibraryManager();
        Book book2 = new Book("The Book(ie)", "Bookster", 1991);
        Book book3 = new Book("We Forgot", "Oops", 404);
        lm2.addBookToLibrary(book2);
        lm2.checkOutBook(1991, "your mom");
        if (lm2.catalog.returnBookByISBN(1991).getBorrowerID() != "your mom") {
            System.out.println("LibraryManager Test 2 FAILED: " + 
                "incorrect borrower id");
            passed = false;
        }
        if (lm2.catalog.returnBookByISBN(1991).isCheckedOut() != true) {
            System.out.println("LibraryManager Test 2 FAILED: " + 
                "isCheckedOut is false");
            passed = false;
        }
        int checkedOutCount1 = lm2.getCheckedOutCount();
        if (checkedOutCount1 != 1) {
            System.out.println("LibraryManager Test 2 FAILED: " + 
                "incorrect checked out coutnt");
            passed = false;
        }
        lm2.checkInBook(1991);
        Book[] search_result = lm2.findAvailableBooksByAuthor("Bookster");
        if (search_result[0].getAuthor() != "Bookster") {
            System.out.println("LibraryManager Test 2 FAILED: " + 
                "findAvailableBooksByAuthor");
            passed = false;
        }

        // Test case 3
        LibraryManager lm3 = new LibraryManager();
        Book book4 = new Book("The Book(ie)", "Bookster", 1991);
        Book book5 = new Book("You're Done Pal", "Krig", 209);
        Book book6 = new Book("Trifling Bastard", "Suckish", 999);
        Book book7 = new Book("You're not that Guy", "Krig", 1738);
        lm3.addBookToLibrary(book4);
        lm3.addBookToLibrary(book5);
        lm3.addBookToLibrary(book6);
        lm3.addBookToLibrary(book7);
        lm3.checkOutBook(1991, "your mom");
        lm3.checkOutBook(999, "Tucker");
        if (lm3.catalog.returnBookByISBN(1991).getBorrowerID() != "your mom") {
            System.out.println("LibraryManager Test 3 FAILED: " + 
                "incorrect borrower id");
            passed = false;
        }
        if (lm3.catalog.returnBookByISBN(209).isCheckedOut() != false) {
            System.out.println("LibraryManager Test 3 FAILED: " + 
                "isCheckedOut is true");
            passed = false;
        }
        int checkedOutCount2 = lm3.getCheckedOutCount();
        if (checkedOutCount2 != 2) {
            System.out.println("LibraryManager Test 3 FAILED: " +  
                "incorrect checked out coutnt");
            passed = false;
        }
        lm3.checkInBook(999);
        Book[] search_result2 = lm3.findAvailableBooksByAuthor("Krig");
        if (search_result2.length != 2) {
            System.out.println("LibraryManager Test 3 FAILED: " + 
                "incorrect search result length");
            passed = false;
        }

        // Test case 4
        LibraryManager lm4 = new LibraryManager();
        Book book8 = new Book("The Bookerlator", "Bookster", 2009);
        Book book9 = new Book("You're One Pal", "Krig", 9099);
        Book book10 = new Book("The Suckertash", "Suckish", 888);
        Book book11 = new Book("Who is this Guy?", "New Guy", 1739);
        lm4.addBookToLibrary(bookToAdd);
        lm4.addBookToLibrary(book2);
        lm4.addBookToLibrary(book3);
        lm4.addBookToLibrary(book4);
        lm4.addBookToLibrary(book5);
        lm4.addBookToLibrary(book6);
        lm4.addBookToLibrary(book7);
        lm4.addBookToLibrary(book8);
        lm4.addBookToLibrary(book9);
        lm4.addBookToLibrary(book10);
        lm4.addBookToLibrary(book11);
        lm4.checkOutBook(1991, "your mom");
        lm4.checkOutBook(999, "Tucker");
        lm4.checkOutBook(1739, "jesus christ");
        lm4.checkOutBook(9099, "jesus christ");
        if (lm4.catalog.returnBookByISBN(1739).getBorrowerID() != 
            "jesus christ") {
            System.out.println("LibraryManager Test 4 FAILED: " + 
                "incorrect borrower id");
            passed = false;
        }
        if (lm4.catalog.returnBookByISBN(209).isCheckedOut() != false) {
            System.out.println("LibraryManager Test 4 FAILED: " + 
                "isCheckedOut is true");
            passed = false;
        }
        int checkedOutCount3 = lm4.getCheckedOutCount();
        if (checkedOutCount3 != 4) {
            System.out.println("LibraryManager Test 4 FAILED: " + 
                "incorrect checked out coutnt");
            passed = false;
        }
        lm4.checkInBook(999);
        Book[] search_result3 = lm4.findAvailableBooksByAuthor("Krig");
        if (search_result3.length != 3) {
            System.out.println("LibraryManager Test 4 FAILED: " + 
                "incorrect search result length");
            passed = false;
        }

        return passed;
    }

    /**
     * main entry point of LibraryManager.java
     * 
     * @param args command line args
     */
    public static void main(String[] args) {
        if (unitTests()) {
            System.out.println("LibraryManager: All unit tests passed.\n");
        } else {
            System.out.println("LibraryManager: ERROR: Failed test.\n");
            return;
        }
    }
}