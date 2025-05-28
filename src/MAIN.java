package src;

public class MAIN {
    public static void main(String[] args) {
        System.out.println("=== Design Patterns Demonstration ===\n");

        // Demo users with different roles
        User adminUser = new User("U001", "Admin User", "admin@example.com", "admin");
        User regularUser = new User("U002", "Regular User", "user@example.com", "user");
        User viewerUser = new User("U003", "Viewer User", "viewer@example.com", "viewer");

        demonstrateProxyPattern(adminUser, regularUser, viewerUser);

        System.out.println("\n" + "=".repeat(50) + "\n");

        demonstrateObserverPattern();
    }

    private static void demonstrateProxyPattern(User adminUser, User regularUser, User viewerUser) {
        System.out.println("=== PROXY PATTERN DEMONSTRATION ===\n");

        // Test with admin user
        System.out.println("--- Testing with Admin User ---");
        DatabaseProxy adminProxy = new DatabaseProxy(adminUser);

        try {
            System.out.println("System Category: " + adminProxy.getSystemCategory());
            adminProxy.setSystemCategory("Production");
            System.out.println("URL: " + adminProxy.getUrl());
            System.out.println("Username: " + adminProxy.getUsername());
            System.out.println("Password: " + adminProxy.getPassword()); // Will be masked
            System.out.println("Report: " + adminProxy.generateReport());
        } catch (SecurityException e) {
            System.out.println("Security Error: " + e.getMessage());
        }

        System.out.println("\n--- Testing with Regular User ---");
        DatabaseProxy userProxy = new DatabaseProxy(regularUser);

        try {
            System.out.println("System Category: " + userProxy.getSystemCategory());
            userProxy.setSystemCategory("Testing"); // Should work
            System.out.println("Trying to access username...");
            userProxy.getUsername(); // Should fail
        } catch (SecurityException e) {
            System.out.println("Security Error: " + e.getMessage());
        }

        System.out.println("\n--- Testing with Viewer User ---");
        DatabaseProxy viewerProxy = new DatabaseProxy(viewerUser);

        try {
            System.out.println("System Category: " + viewerProxy.getSystemCategory());
            System.out.println("Trying to modify system category...");
            viewerProxy.setSystemCategory("Development"); // Should fail
        } catch (SecurityException e) {
            System.out.println("Security Error: " + e.getMessage());
        }

        // Show access log (admin only)
        System.out.println("\n--- Access Log (Admin View) ---");
        try {
            var accessLog = adminProxy.getAccessLog();
            accessLog.forEach((timestamp, entry) -> System.out.println(entry));
        } catch (SecurityException e) {
            System.out.println("Security Error: " + e.getMessage());
        }
    }

    private static void demonstrateObserverPattern() {
        System.out.println("=== OBSERVER PATTERN DEMONSTRATION ===\n");

        // Create report generator with observer capability
        ReportGenerator generator = new ReportGenerator();

        // Create observers
        ReportStatusObserver statusObserver1 = new ReportStatusObserver("StatusMonitor-1");
        ReportStatusObserver statusObserver2 = new ReportStatusObserver("StatusMonitor-2");
        NotificationService notificationService = new NotificationService(true, true);

        // Add observers
        System.out.println("--- Adding Observers ---");
        generator.addObserver(statusObserver1);
        generator.addObserver(statusObserver2);
        generator.addObserver(notificationService);

        System.out.println("\n--- Changing Report Generator Properties ---");
        generator.setStartDate("2024-01-01");
        generator.setEndDate("2024-12-31");
        generator.setOutputFormat("PDF");
        generator.setIntegrationCriteria("standard");

        System.out.println("\n--- Creating Reports ---");
        try {
            Report report1 = generator.createReport("contact");
            System.out.println("Created: " + report1.getClass().getSimpleName());

            Report report2 = generator.createReport("account");
            System.out.println("Created: " + report2.getClass().getSimpleName());

            // Try to create invalid report type
            generator.createReport("invalid");
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        System.out.println("\n--- Removing Observer ---");
        generator.removeObserver(statusObserver1);

        System.out.println("\n--- Final Report Creation ---");
        Report report3 = generator.createReport("system");
        System.out.println("Created: " + report3.getClass().getSimpleName());
    }
}