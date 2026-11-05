package com.selenium.practice.hooks;

import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.selenium.practice.base.TestContext;
import com.selenium.practice.utils.ScenarioLogAppender;
import com.selenium.practice.utils.ScreenShotUtils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class);
    private final TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before
    public void setup(Scenario scenario) {
        // Start capturing scenario-scoped logs on this thread
        ScenarioLogAppender.startRecording(scenario.getName());
        logger.info("================================================================================");
        logger.info(">>> STARTING SCENARIO: [{}]", scenario.getName());
        logger.info("================================================================================");
        
        context.initializeBrowser();
    }

    @After
    public void teardown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                logger.warn("Scenario [{}] FAILED. Capturing failure diagnostics...", scenario.getName());
                
                // 1. Capture and attach screenshot (Stage 4)
                WebDriver driver = context.getDriver();
                if (driver != null) {
                    byte[] screenshot = ScreenShotUtils.captureScreenshotAsBytes(driver);
                    if (screenshot != null && screenshot.length > 0) {
                        String screenshotName = scenario.getName() + " - Failure Screenshot";
                        scenario.attach(screenshot, "image/png", screenshotName);
                        logger.info("Successfully attached failure screenshot to scenario: [{}]", scenario.getName());
                    } else {
                        logger.warn("Screenshot capture returned empty byte array for scenario: [{}]", scenario.getName());
                    }
                } else {
                    logger.warn("WebDriver is null; skipping failure screenshot capture for scenario: [{}]", scenario.getName());
                }

                // 2. Capture and attach scenario-specific execution logs (Stage 5)
                String logs = ScenarioLogAppender.getScenarioLogs();
                if (logs != null && !logs.trim().isEmpty()) {
                    String logAttachmentName = scenario.getName() + " - Execution Logs";
                    scenario.attach(logs.getBytes(StandardCharsets.UTF_8), "text/plain", logAttachmentName);
                    logger.info("Successfully attached execution logs to scenario: [{}]", scenario.getName());
                } else {
                    logger.warn("Scenario log buffer was empty for scenario: [{}]", scenario.getName());
                }
            } else {
                logger.info("Scenario [{}] PASSED. No failure diagnostics required.", scenario.getName());
            }
        } catch (Exception e) {
            // Guard against any diagnostic error masking the original assertion failure
            logger.error("Unexpected error during scenario teardown diagnostic capture: {}", e.getMessage(), e);
        } finally {
            logger.info("================================================================================");
            logger.info("<<< COMPLETED SCENARIO: [{}] - TEARING DOWN BROWSER", scenario.getName());
            logger.info("================================================================================");
            
            // Clean up thread-local log buffer to prevent memory leaks and cross-scenario bleeding
            ScenarioLogAppender.stopAndCleanup();
            context.closeBrowser();
        }
    }
}
