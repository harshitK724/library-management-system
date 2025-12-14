package com.lms.notification;

import com.lms.app.AppLogger;
import java.util.logging.Logger;

public class EmailNotifier implements NotificationObserver {
    private final Logger log = AppLogger.get();
    private final String email;

    public EmailNotifier(String email) { this.email = email; }

    @Override
    public void notify(String message) {
        // no real email integration (assignment says no external APIs)
        log.info("Notify " + email + ": " + message);
    }
}
