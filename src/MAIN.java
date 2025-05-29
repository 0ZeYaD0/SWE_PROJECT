package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MAIN {
    private static final Scanner scanner = new Scanner(System.in);
    private static Map<String, User> users = new HashMap<>();
    private static User currentUser = null;
    private static List<String> categories = new ArrayList<>();
    private static List<String> reports = new ArrayList<>();

    public static void main(String[] args) {
        // Initialize system
        initializeSystem();

        // Login first
        while (currentUser == null) {
            login();
        }

        // Main application loop
        boolean exit = false;
        while (!exit) {
            displayMainMenu();
            int choice = getUserChoice(1, getMaxMenuOption());

            exit = handleMainMenuChoice(choice);
        }

        System.out.println("\nThank you for using the Report Management System. Goodbye!");
        scanner.close();
    }

    private static void initializeSystem() {
        // Initialize users
        users.put("admin", new User("U001", "Admin User", "admin@example.com", "admin"));
        users.put("user", new User("U002", "Regular User", "user@example.com", "user"));
        users.put("viewer", new User("U003", "Viewer User", "viewer@example.com", "viewer"));

        // Initialize some categories
        categories.add("Quarterly Reports");
        categories.add("Financial Statements");
        categories.add("Customer Analytics");

        // Initialize some reports
        reports.add("Q1 2024 Financial Report");
        reports.add("Customer Engagement 2023");
    }

    private static void login() {
        clearScreen();
        printHeader("Report Management System - Login");

        System.out.println("1. Login to system");
        System.out.println("2. Exit program");
        System.out.print("\nEnter your choice (1-2): ");

        int choice = getUserChoice(1, 2);

        if (choice == 2) {
            System.out.println("\nExiting program. Thank you for using the Report Management System!");
            System.exit(0); // Exit the program
        }

        System.out.print("\nUsername (admin/user/viewer): ");
        String username = scanner.nextLine().trim();

        System.out.print("Password (use same as username for demo): ");
        String password = scanner.nextLine().trim();

        // Simple authentication for demonstration
        if (users.containsKey(username) && username.equals(password)) {
            currentUser = users.get(username);
            System.out.println("\n✅ Login successful! Welcome, " + currentUser.getName());
            System.out.println("Role: " + currentUser.getRole());
            pressEnterToContinue();
        } else {
            System.out.println("\n❌ Invalid username or password. Please try again.");
            pressEnterToContinue();
        }
    }

    private static void displayMainMenu() {
        clearScreen();
        printHeader("Report Management System - Main Menu");
        System.out.println("Logged in as: " + currentUser.getName() + " (" + currentUser.getRole() + ")");
        System.out.println("\nOptions:");
        System.out.println("1. View Available Reports");
        System.out.println("2. View Categories");

        // Show options based on user role
        if (!currentUser.getRole().equals("viewer")) {
            System.out.println("3. Create New Report");
            System.out.println("4. Add New Category");
        }

        // Admin-only options
        if (currentUser.getRole().equals("admin")) {
            System.out.println("5. Manage Users");
            System.out.println("6. Access Design Pattern Demonstrations");
            System.out.println("7. Logout");
            System.out.println("8. Exit Program");
        } else if (currentUser.getRole().equals("user")) {
            System.out.println("5. Logout");
            System.out.println("6. Exit Program");
        } else {
            System.out.println("3. Logout");
            System.out.println("4. Exit Program");
        }

        System.out.print("\nEnter your choice: ");
    }

    private static int getMaxMenuOption() {
        if (currentUser.getRole().equals("admin")) {
            return 8; // Increased for Exit option
        } else if (currentUser.getRole().equals("user")) {
            return 6; // Increased for Exit option
        } else {
            return 4; // Increased for Exit option
        }
    }

    private static boolean handleMainMenuChoice(int choice) {
        switch (choice) {
            case 1:
                viewReports();
                return false;
            case 2:
                viewCategories();
                return false;
            case 3:
                if (currentUser.getRole().equals("viewer")) {
                    logout();
                    return false;
                } else {
                    createReport();
                    return false;
                }
            case 4:
                if (currentUser.getRole().equals("viewer")) {
                    // Exit program for viewer
                    System.out.println("\nExiting program. Thank you for using the Report Management System!");
                    return true;
                } else {
                    addCategory();
                    return false;
                }
            case 5:
                if (currentUser.getRole().equals("admin")) {
                    manageUsers();
                    return false;
                } else if (currentUser.getRole().equals("user")) {
                    logout();
                    return false;
                } else {
                    return false; // Should never happen
                }
            case 6:
                if (currentUser.getRole().equals("admin")) {
                    enterDemonstrationMode();
                    return false;
                } else if (currentUser.getRole().equals("user")) {
                    // Exit program for user
                    System.out.println("\nExiting program. Thank you for using the Report Management System!");
                    return true;
                } else {
                    return false; // Should never happen
                }
            case 7:
                if (currentUser.getRole().equals("admin")) {
                    logout();
                    return false;
                } else {
                    return false; // Should never happen
                }
            case 8:
                if (currentUser.getRole().equals("admin")) {
                    // Exit program for admin
                    System.out.println("\nExiting program. Thank you for using the Report Management System!");
                    return true;
                } else {
                    return false; // Should never happen
                }
            default:
                return false;
        }
    }

    // System functionality methods
    private static void viewReports() {
        clearScreen();
        printHeader("Available Reports");

        if (reports.isEmpty()) {
            System.out.println("No reports available.");
        } else {
            System.out.println("Available reports:");
            for (int i = 0; i < reports.size(); i++) {
                System.out.println((i + 1) + ". " + reports.get(i));
            }
        }

        pressEnterToContinue();
    }

    private static void viewCategories() {
        clearScreen();
        printHeader("Report Categories");

        if (categories.isEmpty()) {
            System.out.println("No categories available.");
        } else {
            System.out.println("Available categories:");
            for (int i = 0; i < categories.size(); i++) {
                System.out.println((i + 1) + ". " + categories.get(i));
            }
        }

        pressEnterToContinue();
    }

    private static void createReport() {
        clearScreen();
        printHeader("Create New Report");

        if (categories.isEmpty()) {
            System.out.println("❌ No categories available. Please create a category first.");
            pressEnterToContinue();
            return;
        }

        System.out.print("Enter report name: ");
        String reportName = scanner.nextLine().trim();

        if (reportName.isEmpty()) {
            System.out.println("❌ Report name cannot be empty.");
            pressEnterToContinue();
            return;
        }

        System.out.println("\nAvailable categories:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i));
        }

        System.out.print("\nSelect category (1-" + categories.size() + "): ");
        int categoryChoice = getUserChoice(1, categories.size());

        System.out.println("\n✅ Report '" + reportName + "' created successfully in category '" +
                categories.get(categoryChoice - 1) + "'");
        reports.add(reportName);

        pressEnterToContinue();
    }

    private static void addCategory() {
        clearScreen();
        printHeader("Add New Category");

        System.out.print("Enter category name: ");
        String categoryName = scanner.nextLine().trim();

        if (categoryName.isEmpty()) {
            System.out.println("❌ Category name cannot be empty.");
        } else if (categories.contains(categoryName)) {
            System.out.println("❌ Category already exists.");
        } else {
            categories.add(categoryName);
            System.out.println("✅ Category '" + categoryName + "' added successfully.");
        }

        pressEnterToContinue();
    }

    private static void manageUsers() {
        clearScreen();
        printHeader("User Management");

        System.out.println("Available users:");
        int i = 1;
        for (User user : users.values()) {
            System.out.println(i + ". " + user.getName() + " (" + user.getRole() + ")");
            i++;
        }

        System.out.println("\n1. Add New User");
        System.out.println("2. Return to Main Menu");

        System.out.print("\nEnter your choice (1-2): ");
        int choice = getUserChoice(1, 2);

        if (choice == 1) {
            addNewUser();
        }
    }

    private static void addNewUser() {
        clearScreen();
        printHeader("Add New User");

        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();

        if (username.isEmpty() || users.containsKey(username)) {
            System.out.println("❌ Invalid or existing username.");
            pressEnterToContinue();
            return;
        }

        System.out.print("Enter full name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();

        System.out.println("\nSelect role:");
        System.out.println("1. Admin");
        System.out.println("2. User");
        System.out.println("3. Viewer");

        System.out.print("\nEnter choice (1-3): ");
        int roleChoice = getUserChoice(1, 3);

        String role;
        switch (roleChoice) {
            case 1:
                role = "admin";
                break;
            case 2:
                role = "user";
                break;
            default:
                role = "viewer";
                break;
        }

        // For demo simplicity, set password same as username
        users.put(username, new User("U00" + (users.size() + 1), name, email, role));

        System.out.println("\n✅ User '" + username + "' added successfully with role '" + role + "'");
        System.out.println("For demo purposes, the password is the same as the username.");

        pressEnterToContinue();
    }

    private static void logout() {
        currentUser = null;
        System.out.println("\n✅ Logged out successfully.");

        // After logout, require login again
        while (currentUser == null) {
            login();
        }
    }

    // Demo mode for design pattern demonstrations
    private static void enterDemonstrationMode() {
        boolean exitDemo = false;

        while (!exitDemo) {
            clearScreen();
            printHeader("Design Pattern Demonstrations");
            System.out.println("This is a special mode to demonstrate design patterns.");
            System.out.println("1. Proxy Pattern - Security and Access Control");
            System.out.println("2. Observer Pattern - Event Notification System");
            System.out.println("3. Return to Main System");
            System.out.print("\nEnter your choice (1-3): ");

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
                    exitDemo = true;
                    break;
            }
        }
    }

    // Original demonstration methods below
    private static void demonstrateProxyPattern() {
        printHeader("PROXY PATTERN DEMONSTRATION");
        System.out.println(
                "The Proxy Pattern provides a surrogate or placeholder for another object to control access to it.");
        System.out.println("In this example, DatabaseProxy controls access based on user roles.\n");

        // Use our current system's users for demonstration
        User adminUser = users.get("admin");
        User regularUser = users.get("user");
        User viewerUser = users.get("viewer");

        int userChoice = displayProxyPatternMenu();

        switch (userChoice) {
            case 1:
                testWithUser(adminUser);
                break;
            case 2:
                testWithUser(regularUser);
                break;
            case 3:
                testWithUser(viewerUser);
                break;
            case 4:
                showAccessLog(adminUser);
                break;
            case 5:
                testAllUsers(adminUser, regularUser, viewerUser);
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

    private static void testWithUser(User user) {
        printSubheader("Testing with " + user.getRole() + " User");
        DatabaseProxy proxy = new DatabaseProxy(user);

        try {
            System.out.println("System Category: " + proxy.getSystemCategory());
            proxy.setSystemCategory("Production");
            System.out.println("✅ System category changed to 'Production'");
            System.out.println("URL: " + proxy.getUrl());
            System.out.println("Username: " + proxy.getUsername());
            System.out.println("Password: " + proxy.getPassword()); // Will be masked
            System.out.println("Report: " + proxy.generateReport());
            System.out.println("\n✅ " + user.getRole() + " has full access to all methods");
        } catch (SecurityException e) {
            System.out.println("❌ Security Error: " + e.getMessage());
            if (user.getRole().equals("user")) {
                System.out.println("✅ Regular users cannot access sensitive credentials");
            } else if (user.getRole().equals("viewer")) {
                System.out.println("✅ Viewer users cannot modify system properties");
            }
        }
    }

    private static void showAccessLog(User adminUser) {
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

    private static void testAllUsers(User adminUser, User regularUser, User viewerUser) {
        testWithUser(adminUser);
        testWithUser(regularUser);
        testWithUser(viewerUser);
        showAccessLog(adminUser);
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
}