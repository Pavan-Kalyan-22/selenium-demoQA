package com.selenium.practice.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

/**
 * TestNG listener to track method invocation lifecycle
 * Logs before and after method execution including setup/teardown methods
 */
public class InvokedMethodListener implements IInvokedMethodListener {

    private static final Logger logger = LogManager.getLogger(InvokedMethodListener.class);

    /**
     * Invoked before a method is invoked (includes setup/teardown methods)
     */
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        String methodName = method.getTestMethod().getMethodName();
        String methodType = method.isTestMethod() ? "TEST METHOD" : "CONFIGURATION METHOD";
        
        logger.debug(">>> BEFORE " + methodType + ": " + methodName);
    }

    /**
     * Invoked after a method is invoked (includes setup/teardown methods)
     */
    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        String methodName = method.getTestMethod().getMethodName();
        String methodType = method.isTestMethod() ? "TEST METHOD" : "CONFIGURATION METHOD";
        long executionTime = testResult.getEndMillis() - testResult.getStartMillis();
        
        if (testResult.getStatus() == ITestResult.SUCCESS) {
            logger.debug("<<< AFTER " + methodType + ": " + methodName + " [PASSED] - " + executionTime + " ms");
        } else if (testResult.getStatus() == ITestResult.FAILURE) {
            logger.error("<<< AFTER " + methodType + ": " + methodName + " [FAILED] - " + executionTime + " ms");
        } else if (testResult.getStatus() == ITestResult.SKIP) {
            logger.warn("<<< AFTER " + methodType + ": " + methodName + " [SKIPPED]");
        }
    }
}
