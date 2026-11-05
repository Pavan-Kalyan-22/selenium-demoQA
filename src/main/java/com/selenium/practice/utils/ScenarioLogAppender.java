package com.selenium.practice.utils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.layout.PatternLayout;

/**
 * Thread-safe Log4j2 Appender designed specifically for scenario-scoped log collection.
 * Captures log events emitted during a Cucumber scenario execution on the active thread,
 * automatically masks sensitive credentials, and provides them for diagnostic attachment upon failure.
 */
public class ScenarioLogAppender extends AbstractAppender {

    public static final String APPENDER_NAME = "ScenarioLogAppender";
    private static final ThreadLocal<List<String>> threadLogs = ThreadLocal.withInitial(ArrayList::new);
    private static final ThreadLocal<Boolean> recordingActive = ThreadLocal.withInitial(() -> false);
    private static volatile boolean registered = false;

    private ScenarioLogAppender() {
        super(
            APPENDER_NAME,
            null,
            PatternLayout.newBuilder()
                .withPattern("%d{yyyy-MM-dd HH:mm:ss} [%t] %-5level %logger - %msg%n")
                .build(),
            false,
            Property.EMPTY_ARRAY
        );
    }

    /**
     * Programmatically registers the ScenarioLogAppender to the Log4j2 Root Logger once.
     * Preserves existing Console and File appenders.
     */
    public static synchronized void register() {
        if (!registered) {
            try {
                LoggerContext context = (LoggerContext) LogManager.getContext(false);
                org.apache.logging.log4j.core.config.Configuration config = context.getConfiguration();
                
                Appender existing = config.getAppender(APPENDER_NAME);
                if (existing == null) {
                    ScenarioLogAppender appender = new ScenarioLogAppender();
                    appender.start();
                    config.getRootLogger().addAppender(appender, Level.INFO, null);
                    context.updateLoggers();
                }
                registered = true;
            } catch (Exception e) {
                System.err.println("Failed to register ScenarioLogAppender: " + e.getMessage());
            }
        }
    }

    /**
     * Starts recording logs for the scenario running on the current thread.
     * Clears any stale thread-local log entries.
     *
     * @param scenarioName the name of the scenario starting execution
     */
    public static void startRecording(String scenarioName) {
        register();
        threadLogs.get().clear();
        recordingActive.set(true);
    }

    @Override
    public void append(LogEvent event) {
        if (Boolean.TRUE.equals(recordingActive.get())) {
            try {
                byte[] bytes = getLayout().toByteArray(event);
                if (bytes != null && bytes.length > 0) {
                    String formattedMessage = new String(bytes, StandardCharsets.UTF_8);
                    formattedMessage = maskSensitiveData(formattedMessage);
                    threadLogs.get().add(formattedMessage);
                }
            } catch (Exception ignored) {
                // Ignore internal formatting error to protect main logging pipeline
            }
        }
    }

    /**
     * Retrieves all logs captured during the scenario execution on the current thread.
     *
     * @return consolidated log string, or empty string if no logs were captured
     */
    public static String getScenarioLogs() {
        List<String> logs = threadLogs.get();
        if (logs == null || logs.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String logLine : logs) {
            sb.append(logLine);
        }
        return sb.toString();
    }

    /**
     * Stops recording and removes ThreadLocal references to prevent memory leaks
     * and thread contamination.
     */
    public static void stopAndCleanup() {
        try {
            recordingActive.set(false);
            threadLogs.get().clear();
            threadLogs.remove();
            recordingActive.remove();
        } catch (Exception ignored) {
        }
    }

    /**
     * Sanitizes logs by redacting known sensitive patterns such as passwords or secret keys.
     */
    public static String maskSensitiveData(String message) {
        if (message == null) return null;
        return message
            .replaceAll("(?i)(password\\s*=\\s*['\"]?)([^'\"\\s]+)(['\"]?)", "$1********$3")
            .replaceAll("(?i)(password\\s*:\\s*['\"]?)([^'\"\\s]+)(['\"]?)", "$1********$3")
            .replaceAll("(?i)secret_sauce", "********");
    }
}
