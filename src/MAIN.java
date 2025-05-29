package src;

import java.util.Scanner;

public class MAIN {
    private static final Scanner scanner = new Scanner(System.in);
    private static User adminUser, regularUser, viewerUser;

    public static void main(String[] args) {
        // Initialize users
        adminUser = new User("U001", "Admin User", "admin@example.com", "admin");
        regularUser = new User("U002", "Regular User", "user@example.com", "user");
        viewerUser = new User("U003", "Viewer User", "viewer@example.com", "viewer");

        boolean exit = false;

        while (!exit) {
            displayMainMenu();
            int choice = getUserChoice(1, 3);

            switch (choice) {
                case 1:
                    clearScreen();
                    demonstrateProxyPattern();
                    pressEnterToContinue();
                    break;
                case 2:
                    clearScreen();
                    demonstrateObserverPattern();
                    pressEnterToContinue();
                    break;
                case 3:
                    exit = true;
                    System.out.println("\nThank you for exploring design patterns. Goodbye!");
                    break;
            }
        }

        scanner.close();
    }

    private static void displayMainMenu() {
        clearScreen();
        printHeader("Design Patterns Demonstration");
        System.out.println("1. Proxy Pattern - Security and Access Control");
        System.out.println("2. Observer Pattern - Event Notification System");
        System.out.println("3. Exit Program");
        System.out.print("\nEnter your choice (1-3): ");
    }

    private static int getUserChoice(int min, int max) {
        int choice = -1;
        while (choice < min || choice > max) {
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice < min || choice > max) {
                    System.out.print("Invalid choice. Please enter a number between " + min + " and " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
        return choice;
    }

    private static void demonstrateProxyPattern() {
        printHeader("PROXY PATTERN DEMONSTRATION");
        System.out.println(
                "The Proxy Pattern provides a surrogate or placeholder for another object to control access to it.");
        System.out.println("In this example, DatabaseProxy controls access based on user roles.\n");

        int userChoice = displayProxyPatternMenu();

        switch (userChoice) {
            case 1:
                testWithAdminUser();
                break;
            case 2:
                testWithRegularUser();
                break;
            case 3:
                testWithViewerUser();
                break;
            case 4:
                showAccessLog();
                break;
            case 5:
                testAllUsers();
                break;
        }
    }

    private static int displayProxyPatternMenu() {
        System.out.println("Select a test scenario:");
        System.out.println("1. Test with Admin User");
        System.out.println("2. Test with Regular User");
        System.out.println("3. Test with Viewer User");
        System.out.println("4. Show Access Log (Admin View)");
        System.out.println("5. Run All Tests");
        System.out.print("\nEnter your choice (1-5): ");
        return getUserChoice(1, 5);
    }

    private static void testWithAdminUser() {
        printSubheader("Testing with Admin User");
        DatabaseProxy adminProxy = new DatabaseProxy(adminUser);

        try {
            System.out.println("System Category: " + adminProxy.getSystemCategory());
            adminProxy.setSystemCategory("Production");
            System.out.println("✅ System category changed to 'Production'");
            System.out.println("URL: " + adminProxy.getUrl());
            System.out.println("Username: " + adminProxy.getUsername());
            System.out.println("Password: " + adminProxy.getPassword()); // Will be masked
            System.out.println("Report: " + adminProxy.generateReport());
            System.out.println("\n✅ Admin has full access to all methods");
        } catch (SecurityException e) {
            System.out.println("❌ Security Error: " + e.getMessage());
        }
    }

    private static void testWithRegularUser() {
        printSubheader("Testing with Regular User");
        DatabaseProxy userProxy = new DatabaseProxy(regularUser);

        try {
            System.out.println("System Category: " + userProxy.getSystemCategory());
            userProxy.setSystemCategory("Testing");
            System.out.println("✅ System category changed to 'Testing'");
            System.out.println("Trying to access username...");
            userProxy.getUsername();
        } catch (SecurityException e) {
            System.out.println("❌ Security Error: " + e.getMessage());
            System.out.println("✅ Regular users cannot access sensitive credentials");
        }
    }

    private static void testWithViewerUser() {
        printSubheader("Testing with Viewer User");
        DatabaseProxy viewerProxy = new DatabaseProxy(viewerUser);

        try {
            System.out.println("System Category: " + viewerProxy.getSystemCategory());
            System.out.println("Trying to modify system category...");
            viewerProxy.setSystemCategory("Development");
        } catch (SecurityException e) {
            System.out.println("❌ Security Error: " + e.getMessage());
            System.out.println("✅ Viewer users cannot modify system properties");
        }
    }

    private static void showAccessLog() {
        printSubheader("Access Log (Admin View)");
        DatabaseProxy adminProxy = new DatabaseProxy(adminUser);

        try {
            var accessLog = adminProxy.getAccessLog();
            if (accessLog.isEmpty()) {
                System.out.println("Access log is empty. Run some tests first.");
            } else {
                accessLog.forEach((timestamp, entry) -> System.out.println(entry));
                System.out.println("\n✅ Only admins can view the access logs");
            }
        } catch (SecurityException e) {
            System.out.println("❌ Security Error: " + e.getMessage());
        }
    }

    private static void testAllUsers() {
        testWithAdminUser();
        testWithRegularUser();
        testWithViewerUser();
        showAccessLog();
    }

    private static void demonstrateObserverPattern() {
        printHeader("OBSERVER PATTERN DEMONSTRATION");
        System.out.println("The Observer Pattern defines a one-to-many dependency between objects so that");
        System.out
                .println("when one object changes state, all its dependents are notified and updated automatically.\n");

        int choice = displayObserverPatternMenu();

        // Create report generator with observer capability
        ReportGenerator generator = new ReportGenerator();

        // Create observers
        ReportStatusObserver statusObserver1 = new ReportStatusObserver("StatusMonitor-1");
        ReportStatusObserver statusObserver2 = new ReportStatusObserver("StatusMonitor-2");
        NotificationService notificationService = new NotificationService(true, true);

        switch (choice) {
            case 1:
                demonstrateAddingObservers(generator, statusObserver1, statusObserver2, notificationService);
                break;
            case 2:
                setupObservers(generator, statusObserver1, statusObserver2, notificationService);
                demonstratePropertyChanges(generator);
                break;
            case 3:
                setupObservers(generator, statusObserver1, statusObserver2, notificationService);
                demonstrateReportCreation(generator);
                break;
            case 4:
                setupObservers(generator, statusObserver1, statusObserver2, notificationService);
                demonstrateRemovingObservers(generator, statusObserver1);
                break;
            case 5:
                runFullObserverDemo(generator, statusObserver1, statusObserver2, notificationService);
                break;
        }
    }

    private static int displayObserverPatternMenu() {
        System.out.println("Select a test scenario:");
        System.out.println("1. Adding Observers");
        System.out.println("2. Changing Generator Properties");
        System.out.println("3. Creating Different Reports");
        System.out.println("4. Removing Observers");
        System.out.println("5. Run Full Demonstration");
        System.out.print("\nEnter your choice (1-5): ");
        return getUserChoice(1, 5);
    }

    private static void setupObservers(ReportGenerator generator, ReportStatusObserver observer1,
            ReportStatusObserver observer2, NotificationService notificationService) {
        // Add observers silently (for subsequent demos)
        generator.addObserver(observer1);
        generator.addObserver(observer2);
        generator.addObserver(notificationService);
    }

    private static void demonstrateAddingObservers(ReportGenerator generator, ReportStatusObserver observer1,
            ReportStatusObserver observer2, NotificationService notificationService) {
        printSubheader("Adding Observers");
        System.out.println("Adding StatusMonitor-1...");
        generator.addObserver(observer1);
        System.out.println("Adding StatusMonitor-2...");
        generator.addObserver(observer2);
        System.out.println("Adding NotificationService...");
        generator.addObserver(notificationService);
        System.out.println("\n✅ All observers are now registered and will receive updates");
    }

    private static void demonstratePropertyChanges(ReportGenerator generator) {
        printSubheader("Changing Report Generator Properties");
        generator.setStartDate("2024-01-01");
        generator.setEndDate("2024-12-31");
        generator.setOutputFormat("PDF");
        generator.setIntegrationCriteria("standard");
        System.out.println("\n✅ Notice how each property change notifies all observers");
    }

    private static void demonstrateReportCreation(ReportGenerator generator) {
        printSubheader("Creating Different Types of Reports");
        try {
            Report report1 = generator.createReport("contact");
            System.out.println("Created: " + report1.getClass().getSimpleName());

            Report report2 = generator.createReport("account");
            System.out.println("Created: " + report2.getClass().getSimpleName());

            System.out.println("\nTrying to create an invalid report type...");
            generator.createReport("invalid");
        } catch (Exception e) {
            System.out.println("❌ Exception caught: " + e.getMessage());
            System.out.println("✅ Invalid report types are properly handled");
        }
    }

    private static void demonstrateRemovingObservers(ReportGenerator generator, ReportStatusObserver observer) {
        printSubheader("Removing an Observer");
        System.out.println("Removing StatusMonitor-1...");
        generator.removeObserver(observer);
        System.out.println("Creating another report to demonstrate that removed observer won't receive updates...");

        Report report = generator.createReport("system");
        System.out.println("Created: " + report.getClass().getSimpleName());
        System.out.println("\n✅ Notice that StatusMonitor-1 no longer receives notifications");
    }

    private static void runFullObserverDemo(ReportGenerator generator, ReportStatusObserver observer1,
            ReportStatusObserver observer2, NotificationService notificationService) {
        demonstrateAddingObservers(generator, observer1, observer2, notificationService);
        demonstratePropertyChanges(generator);
        demonstrateReportCreation(generator);
        demonstrateRemovingObservers(generator, observer1);
    }

    // Helper methods for UI
    private static void printHeader(String title) {
        String decoration = "=".repeat(title.length() + 10);
        System.out.println(decoration);
        System.out.println("     " + title);
        System.out.println(decoration + "\n");
    }

    private static void printSubheader(String title) {
        System.out.println("\n--- " + title + " ---");
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        // For Windows, the ANSI escape sequence might not work in all environments,
        // so we also add some newlines as a fallback
        System.out.println("\n\n\n\n\n");
    }

    private static void pressEnterToContinue() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
}