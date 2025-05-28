package src;

public abstract class Report {
    protected String startDate;
    protected String endDate;
    protected String format;
    protected String status;
    protected String data;

    public Report(String startDate, String endDate, String format) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.format = format;
        this.status = "pending";
    }

    // Getters and setters
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

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public abstract String generateReport();
}