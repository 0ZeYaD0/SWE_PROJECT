package src;

public class AccountManagementReport extends Report {
    private String candidateData;
    private String accountData;
    private String applicationData;
    private String reportType;
    private String integrationId;

    public AccountManagementReport(String startDate, String endDate, String format,
            String candidateData, String accountData, String applicationData,
            String reportType, String integrationId) {
        super(startDate, endDate, format);
        this.candidateData = candidateData;
        this.accountData = accountData;
        this.applicationData = applicationData;
        this.reportType = reportType;
        this.integrationId = integrationId;
    }

    // Getters and setters
    public String getCandidateData() {
        return candidateData;
    }

    public void setCandidateData(String candidateData) {
        this.candidateData = candidateData;
    }

    public String getAccountData() {
        return accountData;
    }

    public void setAccountData(String accountData) {
        this.accountData = accountData;
    }

    public String getApplicationData() {
        return applicationData;
    }

    public void setApplicationData(String applicationData) {
        this.applicationData = applicationData;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
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
        this.data = "Account Management Report (" + reportType + ") " +
                "with candidate, account, and application data " +
                "from " + startDate + " to " + endDate;
        this.status = "completed";
        return this.data;
    }
}