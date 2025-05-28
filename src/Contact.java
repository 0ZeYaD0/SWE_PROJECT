package src;

public class Contact {
    private String contactId;
    private String name;
    private String email;
    private String phone;

    public Contact(String contactId, String name, String email, String phone) {
        this.contactId = contactId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // Getters and setters
    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContactInformation(String contactId) {
        return "Contact{id='" + contactId + "', name='" + name +
                "', email='" + email + "', phone='" + phone + "'}";
    }
}
