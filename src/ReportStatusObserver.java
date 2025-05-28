package src;

public class ReportStatusObserver implements Observer {
    private String observerName;

    public ReportStatusObserver(String observerName) {
        this.observerName = observerName;
    }

    @Override
    public void update(String message, Object data) {
        System.out.println("[" + observerName + "] Report Status Update: " + message);
        if (data != null) {
            System.out.println("[" + observerName + "] Additional Data: " + data.toString());
        }
    }

    public String getObserverName() {
        return observerName;
    }
}