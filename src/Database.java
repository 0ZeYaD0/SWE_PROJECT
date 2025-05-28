package src;

public class Database {
    // Singleton instance
    private static Database instance;

    private String systemCategory;
    private String url;
    private String username;
    private String password;

    // Private constructor to prevent instantiation
    private Database(String systemCategory, String url, String username, String password) {
        this.systemCategory = systemCategory;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    // Default private constructor
    private Database() {
        // Initialize with default values
        this.systemCategory = "Default";
        this.url = "jdbc:mysql://localhost:3306/default";
        this.username = "admin";
        this.password = "password";
    }

    // Public method to get the singleton instance
    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    // Public method to get singleton instance with parameters (only sets values if
    // instance doesn't exist)
    public static synchronized Database getInstance(String systemCategory, String url, String username,
            String password) {
        if (instance == null) {
            instance = new Database(systemCategory, url, username, password);
        }
        return instance;
    }

    // Getters and setters
    public String getSystemCategory() {
        return systemCategory;
    }

    public void setSystemCategory(String systemCategory) {
        this.systemCategory = systemCategory;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String generateReport() {
        return "Database report generated from " + systemCategory;
    }

    // Method to reset the singleton instance (useful for testing)
    public static synchronized void resetInstance() {
        instance = null;
    }
}