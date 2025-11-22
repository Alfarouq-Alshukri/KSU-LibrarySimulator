package librarySimulator;
/*      Group Members:

        1. Bassam Al-ghamdi ID: 446106146



        2. Saleh Al-saiqer ID: 446103413



        3. Alfarouq Al-shukri ID: 446109472




        */
import java.util.Scanner;

public class LibrarySimulator {

    // Define members
    static Member member1 = new Member(446106146, "Bassam Al-ghamdi", 0);
    static Member member2 = new Member(446103413, "Saleh Al-saiqer", 0);
    static Member member3 = new Member(446109472, "Alfarouq Al-shukri", 0);

    Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        LibrarySimulator sim = new LibrarySimulator();
        sim.mainMenuLoop();
    }

    // Welcome message
    static void showWelcome() {
        System.out.println("==============================================");
        System.out.println("   Welcome to the KSU Library Simulator");
        System.out.println("==============================================");
    }

    // Display main menu
    static void printMainMenu() {
        System.out.println("\n------------ Main Menu ------------");
        System.out.println("1) Select Account: " + member1.getId() + " (" + member1.getName() + ")");
        System.out.println("2) Select Account: " + member2.getId() + " (" + member2.getName() + ")");
        System.out.println("3) Select Account: " + member3.getId() + " (" + member3.getName() + ")");
        System.out.println("4) Login as Administrator");
        System.out.println("5) Exit");
        System.out.print("Choose an option: ");
    }

    // Main program loop
    void mainMenuLoop() {

        boolean running = true;
        showWelcome();

        while (running) {
            printMainMenu();
            String choice = in.nextLine().trim();

            switch (choice) {

                case "1":
                    runUserSession(member1);
                    break;

                case "2":
                    runUserSession(member2);
                    break;

                case "3":
                    runUserSession(member3);
                    break;

                case "4":
                    runAdminMenu();
                    break;

                case "5":
                    running = false;
                    System.out.println("\nThank you for using the KSU Library Simulator. Goodbye!");
                    break;

                default:
                    System.out.println("\nInvalid selection. Please try again.\n");
                    break;
            }
        }
    }

    // User session menu
    void runUserSession(Member currentMember) {

        while (true) {

            System.out.println("\n---------------------");
            System.out.printf("Welcome %s (ID: %d)%n", currentMember.getName(), currentMember.getId());
            System.out.println("---------------------");
            System.out.println("1) View Borrowed Books Count");
            System.out.println("2) Borrow Book");
            System.out.println("3) Return Book");
            System.out.println("4) View Session Summary");
            System.out.println("5) Exit to Main Menu");
            System.out.print("Choose an option: ");
            String choice = in.nextLine().trim();

            switch (choice) {

                case "1": {
                    // View Borrowed Books Count (also updates view counters)
                    currentMember.viewBorrowedCount();
                    break;
                }

                case "2": {
                    // Borrow Book
                    if (currentMember.borrowOne()) {
                        System.out.printf("Borrow successful. You now have %d book(s).%n",
                                currentMember.getBorrowedCount());
                        System.out.println("Fee: 0.50 credits");
                    } else {
                        System.out.println("Error: You cannot borrow more than 5 books.");
                    }
                    break;
                }

                case "3": {
                    // Return Book
                    if (currentMember.returnOne()) {
                        System.out.printf("Return successful. You now have %d book(s).%n",
                                currentMember.getBorrowedCount());
                        System.out.println("No fee for returns.");
                    } else {
                        System.out.println("Error: You have no books to return.");
                    }
                    break;
                }

                case "4": {
                    // View Session Summary
                    currentMember.displayStatistics();
                    break;
                }

                case "5": {
                    // Exit to Main Menu
                    System.out.println("Logging out and returning to Main Menu...");
                    currentMember.reset();
                    return;
                }

                default: {
                    System.out.println("\nInvalid selection. Please try again.\n");
                    break;
                }
            }
        }
    }

    // Administrator menu
    void runAdminMenu() {
        while (true) {
            System.out.println("\n--- Administrator Menu ---");
            System.out.println("1) View Total Revenue");
            System.out.println("2) Number of Viewing Borrowed Books");
            System.out.println("3) Most Frequent Operation");
            System.out.println("4) Exit to Main Menu");
            System.out.print("Choose an option: ");
            String choice = in.nextLine().trim();

            switch (choice) {
                case "1": {
                    // Same message as original, but using OOP static field
                    System.out.printf("Total Revenue Collected: %.2f credits%n", Member.TotalRevenue);
                    break;
                }

                case "2": {
                    // New feature (extra): View total "view borrowed" operations
                    System.out.println("The total number of viewing borrowed books is "
                            + Member.TotalViewBorrowed + " time(s).");
                    break;
                }

                case "3": {
                    // Same logic and messages as original "Most Frequent Operation"
                    System.out.println("--- Most Frequent Operation ---");
                    System.out.printf("Total Borrows: %d%n", Member.TotalBorrows);
                    System.out.printf("Total Returns: %d%n", Member.TotalReturns);

                    if (Member.TotalBorrows > Member.TotalReturns) {
                        System.out.println("Most Frequent: Borrow");
                    } else if (Member.TotalReturns > Member.TotalBorrows) {
                        System.out.println("Most Frequent: Return");
                    } else {
                        System.out.println("Most Frequent: Borrow and Return (Tied)");
                    }
                    break;
                }

                case "4": {
                    System.out.println("Exiting Admin Menu...");
                    return;
                }

                default: {
                    System.out.println("\nInvalid selection. Please try again.\n");
                    break;
                }
            }
        }
    }
}