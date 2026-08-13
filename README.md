# Selenium Java BDD Automation Framework

> **Portfolio Notice:** This repository is an independent portfolio project created to demonstrate QA automation engineering practices. It does not contain proprietary code or material from any employer or client.

---

## Overview

A maintainable, production-ready UI test automation framework demonstrating professional QA engineering practices. Built with Java, Selenium WebDriver, Cucumber BDD, TestNG, and Allure reporting using the Page Object Model (POM) design pattern.

The framework targets [The Internet](https://the-internet.herokuapp.com/) — a stable, publicly accessible demo site designed for automation practice.

---

## Technology Stack

| Technology | Version | Purpose |
|---|---|---|
| Java | 21 (LTS) | Language |
| Selenium WebDriver | 4.21.0 | Browser automation |
| Cucumber JVM | 7.11.1 | BDD framework |
| TestNG | 7.10.2 | Test execution |
| Maven | 3.9+ | Build tool |
| WebDriverManager | 5.8.0 | Automatic driver management |
| Allure | 2.27.0 | Test reporting |
| Log4j2 | 2.23.1 | Logging |
| Jackson | 2.17.0 | JSON handling |

---

## Architecture

```
Feature Files  (Gherkin — what to test)
      ↓
Step Definitions  (Java — how to test)
      ↓
Page Objects  (Page actions and locators)
      ↓
Base Page  (Reusable Selenium utilities)
      ↓
Driver Factory  (Thread-safe WebDriver management)
      ↓
Browser
```

### Design Principles

- **Page Object Model** — locators and page actions are encapsulated in dedicated page classes
- **BDD with Cucumber** — scenarios written in Gherkin bridge the gap between business and technical teams
- **ThreadLocal WebDriver** — ensures thread-safe execution, enabling parallel test runs
- **Explicit Waits** — `WebDriverWait` with `ExpectedConditions` throughout; no `Thread.sleep()`
- **Centralized Configuration** — `config.properties` with Maven `-D` property overrides
- **Failure Screenshots** — automatically captured and attached to Allure report on scenario failure
- **Reusable Base Page** — common Selenium interactions abstracted into a single class
- **CI/CD Ready** — headless mode, configurable via command-line properties

---

## Project Structure

```
selenium-java-framework/
│
├── src/
│   ├── main/
│   │   ├── java/com/portfolio/automation/
│   │   │   ├── config/          # ConfigManager — property loading with CLI overrides
│   │   │   ├── constants/       # AppConstants — shared string constants
│   │   │   ├── driver/          # DriverFactory — ThreadLocal WebDriver lifecycle
│   │   │   ├── pages/           # Page Objects (BasePage, LoginPage, etc.)
│   │   │   └── utils/           # ScreenshotUtil, TestData
│   │   └── resources/
│   │       ├── config/          # config.properties
│   │       ├── testdata/        # users.json (reference data)
│   │       └── log4j2.xml       # Logging configuration
│   │
│   └── test/
│       ├── java/com/portfolio/automation/
│       │   ├── hooks/           # CucumberHooks (@Before / @After)
│       │   ├── runners/         # TestRunner (Cucumber + TestNG)
│       │   └── stepdefinitions/ # Step definitions per feature area
│       └── resources/
│           └── features/        # Gherkin feature files
│
├── pom.xml
├── testng.xml
├── allure.properties
├── .gitignore
├── .env.example
└── README.md
```

---

## Prerequisites

- **Java 17+** (tested with Java 21)
- **Maven 3.8+**
- **Google Chrome** (latest stable)
- **Git**

> WebDriverManager automatically downloads and configures the matching ChromeDriver — no manual driver installation required.

---

## Installation

```bash
git clone <repository-url>
cd selenium-java-framework
mvn clean compile
```

---

## Running Tests

### Run all tests
```bash
mvn clean test
```

### Run smoke tests only
```bash
mvn test -Dcucumber.filter.tags="@smoke"
```

### Run a specific tag group
```bash
mvn test -Dcucumber.filter.tags="@login"
mvn test -Dcucumber.filter.tags="@regression"
```

### Run headless (e.g. for CI)
```bash
mvn test -Dheadless=true
```

### Run with a different browser
```bash
mvn test -Dbrowser=firefox
mvn test -Dbrowser=edge
```

### Combine options
```bash
mvn test -Dheadless=true -Dcucumber.filter.tags="@smoke"
```

---

## Test Scenarios

| Feature | Tag | Description |
|---|---|---|
| Login — successful | `@smoke @login` | Valid credentials → secure area |
| Login — invalid username | `@regression @login` | Invalid username → error message |
| Login — invalid password | `@regression @login` | Invalid password → error message |
| Secure Area — post-login | `@smoke @secure` | Heading visible after login |
| Secure Area — logout | `@regression @secure` | Logout → redirect to login page |
| Checkboxes — check | `@smoke @checkboxes` | Toggle unchecked checkbox on |
| Checkboxes — uncheck | `@regression @checkboxes` | Toggle checked checkbox off |
| Dropdown — selection | `@smoke @dropdown` | Select options from a dropdown |

---

## Configuration

Edit `src/main/resources/config/config.properties` or override at runtime with Maven properties:

| Property | Default | Description |
|---|---|---|
| `browser` | `chrome` | Browser: `chrome`, `firefox`, `edge` |
| `baseUrl` | `https://the-internet.herokuapp.com/` | Application base URL |
| `explicitWait` | `10` | Wait timeout in seconds |
| `headless` | `false` | Headless browser mode |
| `environment` | `demo` | Environment label |

**Changing the target application:** Update `baseUrl` in `config.properties`. If you migrate to a different demo site (e.g. `https://www.saucedemo.com/`), update the page objects and step definitions to match the new site's HTML structure.

---

## Reporting

### Allure Report (recommended)

After running tests, results are written to `target/allure-results/`.

**Generate and view the report:**

```bash
# Install Allure CLI (one-time setup)
# Windows (Scoop): scoop install allure
# macOS (Homebrew): brew install allure

# Serve the report in a browser
allure serve target/allure-results

# Or generate a static report
allure generate target/allure-results --clean -o target/allure-report
allure open target/allure-report
```

> The framework does not require a local Allure CLI to *run* tests — it only writes result files. The CLI is only needed to *view* the report.

### Cucumber HTML Report

A Cucumber HTML report is also generated at:
```
target/cucumber-reports/cucumber.html
```

### Failure Screenshots

On test failure, screenshots are:
- Automatically captured by the `@After` Cucumber hook
- Saved locally to `screenshots/` with a timestamp
- Attached inline to the Allure report

---

## Extending the Framework

### Add a new browser

In `DriverFactory.java`, add a new case to the switch statement:
```java
case "safari" -> createSafariDriver(headless);
```

### Add a new page

1. Create a new class extending `BasePage` in `src/main/java/.../pages/`
2. Add locators as `By` fields
3. Add public action methods that return the appropriate next page object

### Add a new scenario

1. Add a `Scenario` block to the relevant `.feature` file in `src/test/resources/features/`
2. Add any new step definitions to the relevant step class in `src/test/java/.../stepdefinitions/`

---

## Future Improvements

- **Parallel execution** — configure TestNG suite with parallel threads
- **Cross-browser matrix** — parameterize browser via TestNG data providers
- **Docker / Selenium Grid** — run against a containerized Selenium hub
- **CI/CD pipeline** — GitHub Actions or Jenkins integration
- **API test layer** — RestAssured for backend validation alongside UI tests
- **Environment-specific configuration** — profile-based property files per environment

---

## Demo Site Dependency

This framework tests [https://the-internet.herokuapp.com/](https://the-internet.herokuapp.com/), maintained by Dave Haeffner and Sauce Labs as a permanent automation practice resource. If the site becomes unavailable, update `baseUrl` in `config.properties` to point to an equivalent demo application and update the page objects accordingly.
