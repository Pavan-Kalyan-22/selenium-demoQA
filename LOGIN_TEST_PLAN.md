# Login Module Test Plan and Best Practices

This document describes a professional and maintainable approach for automating the **Login module** of the DemoQA Selenium project. It is written from the perspective of a 3+ year SDET and follows the existing folder structure. The goal is to clearly outline scenarios, code structure, and commands for running the tests.

---

## 🗂 Folder Structure (current)

```
src/
  main/java/base/...
  main/java/com/selenium/practice/driver/
  main/java/com/selenium/practice/pages/
  main/java/com/selenium/practice/utils/

src/
  test/java/com/selenium/practice/base/
  test/java/com/selenium/practice/tests/

resources/
  config.properties
  testng.xml
```

> The `pages` package holds Page Object Model (POM) classes. 
> The `tests` package contains TestNG test classes such as `LoginTest`.

---

## ✅ Test Scenarios

1. **Valid login** – use `standard_user` and correct password.
2. **Invalid login** – correct username with wrong password.
3. **Locked user login** – attempt to login as `locked_out_user`.
4. **Empty username** – leave username blank.
5. **Empty password** – leave password blank.
6. **Both fields empty** – submit with no credentials.
7. **Error message validation** – ensure the proper message is shown for each failure.

Each scenario should be a separate test method or driven via a data provider.

---

## 🛠️ Prerequisites

1. Java 8+ and Maven installed.
2. Chrome/Edge driver managed by `DriverManager` (already present in project).
3. `config.properties` contains base URL and credentials.
4. Run `mvn clean compile` once to ensure build passes.

---

## 🧩 Implementation Guidelines

- Use the **Page Object Model (POM)** for all page interactions. For login:
  - `LoginPage` should expose methods like `enterUsername()`, `enterPassword()`, `clickLogin()`, and getters for error messages.
- Keep assertions inside tests; pages should not contain `Assert` statements.
- Use TestNG `@BeforeClass`/`@BeforeMethod` for setup (driver, open URL) and `@AfterMethod` for teardown (quit driver).
- For multiple scenarios, prefer a `@DataProvider` to avoid duplication.
- Store test data (usernames, passwords, expected messages) in a `Map` or external CSV/JSON if it grows.
- Log steps using a logger if available, otherwise simple `System.out.println`.
- Group tests via TestNG groups (`smoke`, `regression`) for selective runs.
- Use waits (`WaitUtils`) before accessing elements to reduce flakiness.

---

## 📄 Sample `LoginTest.java` Skeleton

```java
package com.selenium.practice.tests;

import com.selenium.practice.base.BaseTest;
import com.selenium.practice.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @Override
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        driver.get(config.getProperty("app.url"));
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
            {"standard_user", "secret_sauce", true, ""},
            {"standard_user", "wrong", false, "Username and password do not match"},
            {"locked_out_user", "secret_sauce", false, "This user has been locked out."},
            {"", "secret_sauce", false, "Username is required"},
            {"standard_user", "", false, "Password is required"},
            {"", "", false, "Username and password are required"}
        };
    }

    @Test(dataProvider = "loginData")
    public void loginScenarios(String user, String pass, boolean shouldSucceed, String expectedError) {
        loginPage.enterUsername(user);
        loginPage.enterPassword(pass);
        loginPage.clickLogin();

        if (shouldSucceed) {
            Assert.assertTrue(loginPage.isLoggedIn(), "User should be logged in");
        } else {
            Assert.assertEquals(loginPage.getErrorMessage(), expectedError);
        }
    }
}
```

> `BaseTest` handles driver initialization/cleanup and reading from `config.properties`.

---

## 🧪 Running the Tests

- Execute all tests:
  ```bash
  mvn clean test
  ```
- Run only login tests:
  ```bash
  mvn -Dtest=LoginTest test
  ```
- Use TestNG suite file (`resources/testng.xml`):
  ```bash
  mvn -DsuiteXmlFile=src/test/resources/testng.xml test
  ```
- On Windows PowerShell:
  ```powershell
  mvn clean test
  ```

> Modify `testng.xml` to include groups (`<groups>`) if you want narrower execution.

---

## 💡 Best Practices & Recommendations

1. **Reusable components**: extract repeated actions (login, logout) into utility classes.
2. **Descriptive test names**: method names should explain scenario and expectation.
3. **Minimal hard‑coding**: avoid literals in tests; use constants or configuration.
4. **Keep tests independent**: each test should set up its own state.
5. **Error validation**: assert not just presence but exact text of messages.
6. **Parallel execution**: enable TestNG parallelism in `testng.xml` for faster feedback.
7. **Continuous integration**: integrate with Jenkins/GitHub Actions, run `mvn test` on every commit.
8. **Reporting**: rely on TestNG's reports or include Allure/Extent for richer results.

---

## 🛠️ Implemented Test Artifacts

The workspace now contains concrete implementations matching the plan:

- **`LoginPage`**: page object with interaction methods and error retrieval.
- **`WaitUtils`**: simple explicit waits for visibility, clickability, and URL checks.
- **`LoginTest`**: TestNG class leveraging a `@DataProvider` covering all login scenarios.
- Updated **`config.properties`** with sauce-demo URL and credentials.

Run the suite with the commands listed above; the tests should execute against `https://www.saucedemo.com`.

---

*Document created by an SDET with 3+ years of automation experience.*