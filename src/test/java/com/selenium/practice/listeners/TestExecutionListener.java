package com.selenium.practice.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * TestNG listener to track test execution lifecycle events
 * Logs test start, success, failure, skip and finish events
 */
public class TestExecutionListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestExecutionListener.class);

    /**
     * Invoked when a test starts
     */
    @Override
    public void onTestStart(ITestResult result) {
        logger.info("========================================");
        logger.info("TEST STARTED: " + result.getMethod().getMethodName());
        logger.info("Test Class: " + result.getTestClass().getName());
        logger.info("========================================");
    }

    /**
     * Invoked when a test passes
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("✓ TEST PASSED: " + result.getMethod().getMethodName());
        logger.info("Execution Time: " + (result.getEndMillis() - result.getStartMillis()) + " ms");
        logger.info("========================================");
    }

    /**
     * Invoked when a test fails
     */
    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("✗ TEST FAILED: " + result.getMethod().getMethodName());
        logger.error("Failure Reason: " + result.getThrowable().getMessage());
        logger.error("Execution Time: " + (result.getEndMillis() - result.getStartMillis()) + " ms");
        
        if (result.getThrowable() != null) {
            logger.error("Stack Trace: ", result.getThrowable());
        }
        logger.error("========================================");
    }

    /**
     * Invoked when a test is skipped
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("⊘ TEST SKIPPED: " + result.getMethod().getMethodName());
        logger.warn("Skip Reason: " + result.getThrowable());
        logger.warn("========================================");
    }

    /**
     * Invoked when a test fails but within success percentage
     */
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        logger.warn("⊙ TEST FAILED BUT WITHIN SUCCESS PERCENTAGE: " + result.getMethod().getMethodName());
        logger.warn("========================================");
    }

    /**
     * Invoked when all tests finish
     */
    @Override
    public void onFinish(ITestContext context) {
        logger.info("\n========================================");
        logger.info("TEST SUITE FINISHED");
        logger.info("Suite Name: " + context.getName());
        logger.info("Total Tests: " + context.getAllTestMethods().length);
        logger.info("Passed: " + context.getPassedTests().size());
        logger.info("Failed: " + context.getFailedTests().size());
        logger.info("Skipped: " + context.getSkippedTests().size());
        logger.info("========================================\n");
    }

    /**
     * Invoked when test suite starts
     */
    @Override
    public void onStart(ITestContext context) {
        logger.info("\n========================================");
        logger.info("TEST SUITE STARTED");
        logger.info("Suite Name: " + context.getName());
        logger.info("========================================\n");
    }
}
