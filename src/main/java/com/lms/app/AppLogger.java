package com.lms.app;

import java.util.logging.*;

public final class AppLogger {
    private static final Logger LOGGER = Logger.getLogger("LMS");

    static {
        LOGGER.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.INFO);
        handler.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(handler);
        LOGGER.setLevel(Level.INFO);
    }

    private AppLogger() {}
    public static Logger get() { return LOGGER; }
}
