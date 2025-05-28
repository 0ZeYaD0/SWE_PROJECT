package src;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class DatabaseProxy implements DatabaseInterface {
    private Database realDatabase;
    private User currentUser;
    private Map<String, String> accessLog;
    private boolean isConnected;

    public DatabaseProxy(User currentUser) {
        this.currentUser = currentUser;
        this.accessLog = new HashMap<>();
        this.isConnected = false;
    }

    // Lazy loading - create database connection only when needed
    private Database getRealDatabase() {
        if (realDatabase == null) {
            logAccess("Database connection initialized (lazy loading)");
            realDatabase = Database.getInstance();
            isConnected = true;
        }
        return realDatabase;
    }

    // Access control based on user role
    private boolean hasAccess(String operation) {
        String role = currentUser.getRole().toLowerCase();

        switch (operation.toLowerCase()) {
            case "read":
                return role.equals("admin") || role.equals("user") || role.equals("viewer");
            case "write":
                return role.equals("admin") || role.equals("user");
            case "admin":
                return role.equals("admin");
            default:
                return false;
        }
    }

    private void logAccess(String operation) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String logEntry = "[" + timestamp + "] User: " + currentUser.getName() +
                " (" + currentUser.getRole() + ") - " + operation;
        accessLog.put(timestamp, logEntry);
        System.out.println("[PROXY LOG] " + logEntry);
    }

    @Override
    public String getSystemCategory() {
        if (!hasAccess("read")) {
            logAccess("DENIED - getSystemCategory");
            throw new SecurityException("Access denied: Insufficient privileges to read system category");
        }

        logAccess("SUCCESS - getSystemCategory");
        return getRealDatabase().getSystemCategory();
    }

    @Override
    public void setSystemCategory(String systemCategory) {
        if (!hasAccess("write")) {
            logAccess("DENIED - setSystemCategory");
            throw new SecurityException("Access denied: Insufficient privileges to modify system category");
        }

        logAccess("SUCCESS - setSystemCategory: " + systemCategory);
        getRealDatabase().setSystemCategory(systemCategory);
    }

    @Override
    public String getUrl() {
        if (!hasAccess("read")) {
            logAccess("DENIED - getUrl");
            throw new SecurityException("Access denied: Insufficient privileges to read URL");
        }

        logAccess("SUCCESS - getUrl");
        return getRealDatabase().getUrl();
    }

    @Override
    public void setUrl(String url) {
        if (!hasAccess("admin")) {
            logAccess("DENIED - setUrl");
            throw new SecurityException("Access denied: Admin privileges required to modify URL");
        }

        logAccess("SUCCESS - setUrl: " + url);
        getRealDatabase().setUrl(url);
    }

    @Override
    public String getUsername() {
        if (!hasAccess("admin")) {
            logAccess("DENIED - getUsername");
            throw new SecurityException("Access denied: Admin privileges required to view username");
        }

        logAccess("SUCCESS - getUsername");
        return getRealDatabase().getUsername();
    }

    @Override
    public void setUsername(String username) {
        if (!hasAccess("admin")) {
            logAccess("DENIED - setUsername");
            throw new SecurityException("Access denied: Admin privileges required to modify username");
        }

        logAccess("SUCCESS - setUsername: " + username);
        getRealDatabase().setUsername(username);
    }

    @Override
    public String getPassword() {
        if (!hasAccess("admin")) {
            logAccess("DENIED - getPassword");
            throw new SecurityException("Access denied: Admin privileges required to view password");
        }

        logAccess("SUCCESS - getPassword (returning masked)");
        // Return masked password for security
        return "****";
    }

    @Override
    public void setPassword(String password) {
        if (!hasAccess("admin")) {
            logAccess("DENIED - setPassword");
            throw new SecurityException("Access denied: Admin privileges required to modify password");
        }

        logAccess("SUCCESS - setPassword (password updated)");
        getRealDatabase().setPassword(password);
    }

    @Override
    public String generateReport() {
        if (!hasAccess("read")) {
            logAccess("DENIED - generateReport");
            throw new SecurityException("Access denied: Insufficient privileges to generate report");
        }

        logAccess("SUCCESS - generateReport");
        return getRealDatabase().generateReport();
    }

    // Additional proxy methods
    public Map<String, String> getAccessLog() {
        if (!hasAccess("admin")) {
            throw new SecurityException("Access denied: Admin privileges required to view access log");
        }
        return new HashMap<>(accessLog);
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setCurrentUser(User user) {
        logAccess("User switched from " + currentUser.getName() + " to " + user.getName());
        this.currentUser = user;
    }
}