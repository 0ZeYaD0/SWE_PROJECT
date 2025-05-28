package src;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotificationService implements Observer {
    private boolean emailEnabled;
    private boolean smsEnabled;

    public NotificationService(boolean emailEnabled, boolean smsEnabled) {
        this.emailEnabled = emailEnabled;
        this.smsEnabled = smsEnabled;
    }

    @Override
    public void update(String message, Object data) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        if (emailEnabled) {
            sendEmail(message, timestamp);
        }

        if (smsEnabled) {
            sendSMS(message, timestamp);
        }

        logNotification(message, timestamp);
    }

    private void sendEmail(String message, String timestamp) {
        System.out.println("[EMAIL] " + timestamp + " - " + message);
    }

    private void sendSMS(String message, String timestamp) {
        System.out.println("[SMS] " + timestamp + " - " + message);
    }

    private void logNotification(String message, String timestamp) {
        System.out.println("[LOG] " + timestamp + " - Notification sent: " + message);
    }

    public void setEmailEnabled(boolean emailEnabled) {
        this.emailEnabled = emailEnabled;
    }

    public void setSmsEnabled(boolean smsEnabled) {
        this.smsEnabled = smsEnabled;
    }
}