package src;

public class ContactCategory {
    private String code;
    private String description;

    public ContactCategory(String code, String description) {
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

    public String getContactCategory(String code, String description) {
        return "ContactCategory{code='" + code + "', description='" + description + "'}";
    }
}
