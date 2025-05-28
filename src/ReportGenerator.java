package src;

public class ReportGenerator {
    private String reportType;
    private String startDate;
    private String endDate;
    private String outputFormat;
    private String integrationCriteria;
    private String status;

    public ReportGenerator() {
        this.status = "ready";
    }

    // Getters and setters
    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    public String getIntegrationCriteria() {
        return integrationCriteria;
    }

    public void setIntegrationCriteria(String integrationCriteria) {
        this.integrationCriteria = integrationCriteria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Report createReport(String type) {
        this.status = "creating";
        Report report = null;

        switch (type.toLowerCase()) {
            case "contact":
                report = new ContactCategoryReport(startDate, endDate, outputFormat,
                        "default", integrationCriteria);
                break;
            case "account":
                report = new AccountManagementReport(startDate, endDate, outputFormat,
                        "candidates", "accounts", "applications",
                        "summary", "default");
                break;
            case "system":
                report = new ContactCategorySystemReport(startDate, endDate, outputFormat,
                        "system", "contact123", "integration456");
                break;
            case "auth":
                report = new AuthenticationManagementReport(startDate, endDate, outputFormat,
                        "auth123", "system");
                break;
            default:
                throw new IllegalArgumentException("Unknown report type: " + type);
        }

        this.status = "ready";
        return report;
    }
}
