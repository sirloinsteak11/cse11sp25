///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:              Book
// Files:              Book.java
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
 * CREATES A VIRTUAL BOOK USING THE GIVEN TITLE AUTHOR NAME AND ISBN
 * CAN BE CHECKED OUT WITH A BORROWER ID
 * 
 * BUGS: NONE AFAIK
 * 
 * @author deonn almirol
 */
public class Book {

    private String title;
    private String author;
    private long isbn;
    private String borrowerID;
    private boolean isCheckedOut;

    /**
     * no arg constructor, sets strings to null,
     * isbn to zero, and boolean to false
     */
    public Book() {
        this.title = null;
        this.author = null;
        this.isbn = 0;
        this.borrowerID = null;
        this.isCheckedOut = false;
    }

    /**
     * arg constructor, initializes based on given args
     * 
     * 
     * @param title title of book
     * @param author name of author
     * @param isbn large number isbn
     */
    public Book(String title, String author, long isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.borrowerID = null;
        this.isCheckedOut = false;
    }

    /**
     * string getter, returns title of book
     * 
     * @return title of book
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * string getter, returns name of author
     * 
     * @return name of author
     */
    public String getAuthor() {
        return this.author;
    }
    
    /**
     * long getter, returns long isbn of book
     * 
     * 
     * @return isbn of book
     */
    public long getIsbn() {
        return this.isbn;
    }

    /**
     * string getter, returns borrower id
     * 
     * 
     * @return borrower id
     */
    public String getBorrowerID() {
        return this.borrowerID;
    }

    /**
     * bool getter, returns check out status of book
     * 
     * 
     * @return checked out status
     */
    public boolean isCheckedOut() {
        return this.isCheckedOut;
    }

    /**
     * string setter, sets title of book
     * 
     * 
     * @param title new title of book
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * string setter, sets author of book
     * 
     * @param author new name of author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * long setter, sets isbn of book
     * 
     * @param isbn new isbn
     */
    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    /**
     * string setter, sets new borrower id of book
     * 
     * @param borrowerID new borrower id
     */
    public void setBorrowerID(String borrowerID) {
        this.borrowerID = borrowerID;
    }

    /**
     * bool setter, sets checked out status of book
     * 
     * @param isCheckedOut new checked out status
     */
    public void setIsCheckedOut(boolean isCheckedOut) {
        this.isCheckedOut = isCheckedOut;
    }

    /**
     * performs unit tests
     * 
     * @return bool determining if all tests passed or not
     */
    @SuppressWarnings("checkstyle:MagicNumber") // DO NOT CHANGE THIS LINE!!!
    public static boolean unitTests() {
        boolean passed = true;

        //Test Case 1: set author
        Book book1 = new Book();
        book1.setAuthor("Joey Bowtie");
        String expectedOutput = "Joey Bowtie";
        System.out.println(String.format
            ("expected output is %s but actual output is %s", 
                expectedOutput, book1.author));
        if (book1.author != expectedOutput) {
            return false;
        }

        //Test Case 2: set isbn
        Book book2 = new Book();
        book2.setIsbn(1099l);
        long expectedOutput2 = 1099l;
        System.out.println(String.format
            ("expected output is %d but actual output is %d", 
                expectedOutput2, book2.isbn));
        if (book2.isbn != expectedOutput2) {
            return false;
        }

        //Test Case 3: get isCheckedOut
        Book book3 = new Book("The Can of Worms", "idiot", 9919191910101l);
        boolean actualOutput = book3.isCheckedOut();
        boolean expectedOutput3 = false;
        System.out.println(String.format
            ("expected output is %b but actual output is %b", 
                expectedOutput3, actualOutput));
        if (expectedOutput3 != actualOutput) {
            return false;
        }
        
        //Test Case 4: get title
        Book book4 = new Book("American Psycho",
             "Creator of Patrick Bateman", 69l);
        String actualOutput2 = book4.getTitle();
        String expectedOutput4 = "American Psycho";
        System.out.println(String.format
            ("expected output is %s but actual output is %s", 
                expectedOutput4, actualOutput2));
        if (expectedOutput4 != actualOutput2) {
            return false;
        }

        return passed;
    }

    /**
     * main entry point of Book.java
     * 
     * @param args command line args
     */
    public static void main(String[] args) {
        if (unitTests()) {
            System.out.println("Book: All unit tests passed.\n");
        } else {
            System.out.println("Book: ERROR: Failed test.\n");
            return;
        }
    }
}