package src;

public class ReportSystemDemo {
    public static void main(String[] args) {
        // Create report generator
        ReportGenerator generator = new ReportGenerator();
        generator.setStartDate("2024-01-01");
        generator.setEndDate("2024-12-31");
        generator.setOutputFormat("PDF");
        generator.setIntegrationCriteria("standard");

        // Generate different types of reports
        System.out.println("=== Report Generation System Demo ===");

        // Contact Category Report
        Report contactReport = generator.createReport("contact");
        System.out.println("1. " + contactReport.generateReport());

        // Account Management Report
        Report accountReport = generator.createReport("account");
        System.out.println("2. " + accountReport.generateReport());

        // Contact Category System Report
        Report systemReport = generator.createReport("system");
        System.out.println("3. " + systemReport.generateReport());

        // Authentication Management Report
        Report authReport = generator.createReport("auth");
        System.out.println("4. " + authReport.generateReport());

        // Demonstrate other classes
        System.out.println("\n=== Supporting Classes Demo ===");

        Database db = Database.getInstance("MySQL", "jdbc:mysql://localhost:3306/reports", "admin", "password");
        System.out.println("5. " + db.generateReport());

        ContactCategory category = new ContactCategory("CC001", "Business Contacts");
        System.out.println("6. " + category.getContactCategory("CC001", "Business Contacts"));

        AuthenticationAccount authAccount = new AuthenticationAccount("AUTH001", "Admin Account");
        System.out.println("7. " + authAccount.getAuthenticationAccount("AUTH001", "Admin Account"));

        Contact contact = new Contact("C001", "John Doe", "john@example.com", "123-456-7890");
        System.out.println("8. " + contact.getContactInformation("C001"));

        User user = new User("U001", "Jane Smith", "jane@example.com", "Administrator");
        System.out.println("9. " + user.getLoginInformation("U001", "Administrator"));
    }
}
