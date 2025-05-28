package src;

public class AuthenticationManagementReport extends Report {
    private String startDate;
    private String endDate;
    private String authId;
    private String source;

    public AuthenticationManagementReport(String startDate, String endDate, String format,
            String authId, String source) {
        super(startDate, endDate, format);
        this.authId = authId;
        this.source = source;
    }

    // Getters and setters
    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String generateReport() {
        this.status = "generating";
        // Simulate report generation logic
        this.data = "Authentication Management Report " +
                "(Auth ID: " + authId + ", Source: " + source + ") " +
                "from " + startDate + " to " + endDate;
        this.status = "completed";
        return this.data;
    }
}
