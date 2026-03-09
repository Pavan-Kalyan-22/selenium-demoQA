# selenium-demoQA

This repository contains a professional Selenium automation framework structured as a Maven project. The layout follows standard conventions:

```
selenium-demoQA/
├── pom.xml              # Maven project descriptor
├── src/main/java        # reusable framework code (drivers, pages, utilities)
├── src/test/java        # test cases and base classes
├── src/test/resources   # configuration files and TestNG suites
├── target/              # build output (ignored by git)
```

## Getting started

1. **Prerequisites**
   - JDK 1.8 (or later) installed and `JAVA_HOME` set
   - Maven installed (`mvn` on your PATH)

2. **Running the tests**

   ```bash
   mvn clean test
   ```

   The TestNG suite file (`src/test/resources/testng.xml`) controls which packages are executed.

3. **Configuration**
   - `src/test/resources/config.properties` holds environment-specific properties (browser, URL, etc.)

4. **Project conventions**
   - Framework helper classes (driver manager, page objects, utilities) live under `src/main/java`
   - Test classes extend `com.selenium.practice.base.BaseTest` and reside in `src/test/java/com/selenium/practice/tests`.

You can extend the framework by adding new page objects under `pages/`, utilities under `utils/`, and tests under `tests/`.

Happy automating!