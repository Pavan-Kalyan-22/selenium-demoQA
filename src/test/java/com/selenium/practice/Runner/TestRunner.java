package com.selenium.practice.Runner;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.FeatureWrapper;

@CucumberOptions (
    features = "src/test/resources/features/AddSingleProduct.feature",
    glue = {
        "com.selenium.practice.steps",
        "com.selenium.practice.hooks"
    },
    plugin = {
        "pretty",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"

    },
    monochrome = true
)

public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @Override
    @Test(
        groups = "cucumber",
        description = "Runs Cucumber Scenarios",
        dataProvider = "scenarios"

    )
    public void runScenario(
            PickleWrapper pickleWrapper,
            FeatureWrapper featureWrapper) {

        super.runScenario(pickleWrapper, featureWrapper);
    }
}