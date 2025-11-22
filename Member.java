package librarySimulator;
/*      Group Members:

        1. Bassam Al-ghamdi ID: 446106146



        2. Saleh Al-saiqer ID: 446103413



        3. Alfarouq Al-shukri ID: 446109472




        */
public class Member {

    // Instance variables
    private int id;
    private String name;
    private int borrowedCount;

    // Session statistics (per login)
    private int numViewBorrowed;
    private int numBorrows;
    private int numReturns;
    private double sessionFees;

    // Static (global) statistics
    public static double TotalRevenue = 0.0;
    public static int TotalViewBorrowed = 0;
    public static int TotalBorrows = 0;
    public static int TotalReturns = 0;

    // Constructor
    public Member(int id, String name, int borrowedCount) {
        this.id = id;
        this.name = name;
        this.borrowedCount = borrowedCount;

        // Session stats start at zero
        this.numViewBorrowed = 0;
        this.numBorrows = 0;
        this.numReturns = 0;
        this.sessionFees = 0.0;
    }

    // Getters (as needed)
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getBorrowedCount() {
        return this.borrowedCount;
    }

    // Private helpers (as in UML: "- canBorrow", "- canReturn")

    private boolean canBorrow() {
        // Limit is 5 books max
        return borrowedCount < 5;
    }

    private boolean canReturn() {
        // Must have at least 1 book to return
        return borrowedCount > 0;
    }

    // --- Public operations ---

    // View borrowed count
    public void viewBorrowedCount() {
        System.out.printf("You currently have %d borrowed book(s).%n", this.borrowedCount);
        this.numViewBorrowed++;
        TotalViewBorrowed++;
    }

    // Borrow one book (returns true if successful)
    public boolean borrowOne() {
        if (!canBorrow()) {
            return false;
        }

        this.borrowedCount++;
        this.numBorrows++;
        this.sessionFees += 0.50;  // Charge 0.50 fee

        // Update global library stats
        TotalRevenue += 0.50;
        TotalBorrows++;

        return true;
    }

    // Return one book (returns true if successful)
    public boolean returnOne() {
        if (!canReturn()) {
            return false;
        }

        this.borrowedCount--;
        this.numReturns++;

        // Update global stats
        TotalReturns++;

        return true;
    }

    // Display session statistics
    public void displayStatistics() {
        System.out.println("\n----- Session Summary -----");
        System.out.printf("Books borrowed this session: %d%n", this.numBorrows);
        System.out.printf("Books returned this session: %d%n", this.numReturns);
        System.out.printf("Fees incurred this session:  %.2f credits%n", this.sessionFees);
        System.out.println("---------------------------");
    }

    // Reset session statistics
    public void reset() {
        this.numViewBorrowed = 0;
        this.numBorrows = 0;
        this.numReturns = 0;
        this.sessionFees = 0.0;
    }
}