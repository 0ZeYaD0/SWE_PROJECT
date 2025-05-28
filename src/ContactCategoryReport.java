package src;

// ContactCategoryReport class
public class ContactCategoryReport extends Report {
    private String contactCategory;
    private String integrationCriteria;

    public ContactCategoryReport(String startDate, String endDate, String format,
            String contactCategory, String integrationCriteria) {
        super(startDate, endDate, format);
        this.contactCategory = contactCategory;
        this.integrationCriteria = integrationCriteria;
    }

    // Getters and setters
    public String getContactCategory() {
        return contactCategory;
    }

    public void setContactCategory(String contactCategory) {
        this.contactCategory = contactCategory;
    }

    public String getIntegrationCriteria() {
        return integrationCriteria;
    }

    public void setIntegrationCriteria(String integrationCriteria) {
        this.integrationCriteria = integrationCriteria;
    }

    @Override
    public String generateReport() {
        this.status = "generating";
        // Simulate report generation logic
        this.data = "Contact Category Report for " + contactCategory +
                " from " + startDate + " to " + endDate;
        this.status = "completed";
        return this.data;
    }
}