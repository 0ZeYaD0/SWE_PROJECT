package src;

public class Database {
    private String systemCategory;
    private String url;
    private String username;
    private String password;

    public Database(String systemCategory, String url, String username, String password) {
        this.systemCategory = systemCategory;
        this.url = url;
        this.username = username;
        this.password = password;
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
}
