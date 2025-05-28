package src;

import java.util.ArrayList;
import java.util.List;

public class ReportGenerator implements Subject {
    private String reportType;
    private String startDate;
    private String endDate;
    private String outputFormat;
    private String integrationCriteria;
    private String status;
    private List<Observer> observers;

    public ReportGenerator() {
        this.status = "ready";
        this.observers = new ArrayList<>();
    }

    // Observer pattern methods
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
        notifyObservers("Observer added to ReportGenerator", observer.getClass().getSimpleName());
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
        notifyObservers("Observer removed from ReportGenerator", observer.getClass().getSimpleName());
    }

    @Override
    public void notifyObservers(String message, Object data) {
        for (Observer observer : observers) {
            observer.update(message, data);
        }
    }

    // Getters and setters with notifications
    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
        notifyObservers("Report type changed", reportType);
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
        notifyObservers("Start date changed", startDate);
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
        notifyObservers("End date changed", endDate);
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
        notifyObservers("Output format changed", outputFormat);
    }

    public String getIntegrationCriteria() {
        return integrationCriteria;
    }

    public void setIntegrationCriteria(String integrationCriteria) {
        this.integrationCriteria = integrationCriteria;
        notifyObservers("Integration criteria changed", integrationCriteria);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        String oldStatus = this.status;
        this.status = status;
        notifyObservers("Status changed from " + oldStatus + " to " + status, status);
    }

    public Report createReport(String type) {
        this.status = "creating";
        notifyObservers("Report creation started", type);

        Report report = null;

        try {
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
                    notifyObservers("Report creation failed - Unknown type", type);
                    throw new IllegalArgumentException("Unknown report type: " + type);
            }

            this.status = "ready";
            notifyObservers("Report creation completed successfully", type);

        } catch (Exception e) {
            this.status = "error";
            notifyObservers("Report creation failed with error", e.getMessage());
            throw e;
        }

        return report;
    }
}