
package com.selenium.practice.hooks;

import com.selenium.practice.base.TestContext;

import io.cucumber.java.Before;
import io.cucumber.java.After;

public class Hooks {

    private final TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before
    public void setup() {
        context.initializeBrowser();
    }

    @After
    public void teardown() {
        context.closeBrowser();
    }
}
