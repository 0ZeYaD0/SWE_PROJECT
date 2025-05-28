package src;

public class ContactCategorySystemReport extends Report {
    private String startDate;
    private String endDate;
    private String systemCategory;
    private String contactId;
    private String integrationId;

    public ContactCategorySystemReport(String startDate, String endDate, String format,
            String systemCategory, String contactId, String integrationId) {
        super(startDate, endDate, format);
        this.systemCategory = systemCategory;
        this.contactId = contactId;
        this.integrationId = integrationId;
    }

    // Getters and setters
    public String getSystemCategory() {
        return systemCategory;
    }

    public void setSystemCategory(String systemCategory) {
        this.systemCategory = systemCategory;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getIntegrationId() {
        return integrationId;
    }

    public void setIntegrationId(String integrationId) {
        this.integrationId = integrationId;
    }

    @Override
    public String generateReport() {
        this.status = "generating";
        // Simulate report generation logic
        this.data = "Contact Category System Report for " + systemCategory +
                " (Contact ID: " + contactId + ") " +
                "from " + startDate + " to " + endDate;
        this.status = "completed";
        return this.data;
    }
}
