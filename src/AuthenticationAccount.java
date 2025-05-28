package src;

public class AuthenticationAccount {
    private String code;
    private String description;

    public AuthenticationAccount(String code, String description) {
        this.code = code;
        this.description = description;
    }

    // Getters and setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthenticationAccount(String code, String description) {
        return "AuthenticationAccount{code='" + code + "', description='" + description + "'}";
    }
}
