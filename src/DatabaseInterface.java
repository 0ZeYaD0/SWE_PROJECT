package src;

public interface DatabaseInterface {
    String getSystemCategory();

    void setSystemCategory(String systemCategory);

    String getUrl();

    void setUrl(String url);

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    String generateReport();
}