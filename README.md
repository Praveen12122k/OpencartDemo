# OpenCart UI Automation Framework

A UI test automation framework for the **OpenCart** e-commerce application, built with **Java + Selenium WebDriver + TestNG** using the **Page Object Model (POM)** design pattern.

The framework automates common user journeys — account registration, login (single and data-driven), and account verification — and supports cross-browser runs, Selenium Grid / Docker execution, logging, screenshots on failure, and rich HTML reporting.

---

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [Configuration](#configuration)
- [How to Run the Tests](#how-to-run-the-tests)
- [TestNG Suite Files](#testng-suite-files)
- [Reports, Logs & Screenshots](#reports-logs--screenshots)
- [Test Cases](#test-cases)
- [Author](#author)

---

## Features

- **Page Object Model (POM)** — each web page has its own class, keeping locators and actions separate from tests. Easy to read and maintain.
- **Data-Driven Testing (DDT)** — login is tested with multiple email/password combinations read from an Excel file (`Apache POI`).
- **Cross-browser testing** — runs on Chrome and Edge, selected dynamically through TestNG parameters.
- **Local & Remote (Grid) execution** — switch between running on your own machine and a Selenium Grid / Docker setup by changing one property.
- **Automatic screenshots** on test failure, saved with a timestamp.
- **Logging** with Log4j2 (console + file).
- **Rich HTML reports** via ExtentReports, plus TestNG's default reports.
- **Reusable helpers** for random data generation (random strings, numbers, alphanumeric values).

---

## Tech Stack

| Purpose | Tool / Library |
|---|---|
| Language | Java 21 |
| Browser automation | Selenium WebDriver 4.41.0 |
| Test runner | TestNG 7.12.0 |
| Build & dependencies | Maven |
| Excel (data-driven) | Apache POI 5.5.1 |
| Reporting | ExtentReports 5.1.2 |
| Logging | Log4j2 2.25.3 |
| Utilities | Commons IO, Commons Lang3 |

---

## Project Structure

```
OpenCart-UI-Automation-Framework/
├── src/
│   ├── main/java/Opencartv121/
│   │   └── Main.java
│   └── test/
│       ├── java/
│       │   ├── basepage/
│       │   │   └── BasePage.java            # Common base for page objects
│       │   ├── pageObjects/                 # Page Object Model classes
│       │   │   ├── HomePage.java
│       │   │   ├── RegisterAccountPage.java
│       │   │   ├── LoginAccountPage.java
│       │   │   └── MyAccountPage.java
│       │   ├── testBase/
│       │   │   └── BaseTest.java            # Driver setup/teardown, config, utilities
│       │   ├── testCases/                   # Actual test classes
│       │   │   ├── TC001_RegisterAccountTest.java
│       │   │   ├── TC002_LoginAccountTest.java
│       │   │   └── TC003_LoginAccountDDT.java
│       │   └── utilities/
│       │       ├── DataProviders.java        # Feeds Excel data into tests
│       │       ├── ExcelUtility.java         # Reads/writes .xlsx files
│       │       └── ExtentReportManager.java  # TestNG listener for reports
│       └── resources/
│           ├── config.properties            # App URL, credentials, environment
│           └── log4j2.xml                    # Logging configuration
├── testData/
│   └── OpenCart_Logindata.xlsx              # Login test data
├── master.xml                               # Default TestNG suite
├── crossbrowser.xml                         # Parallel Chrome + Edge suite
├── grouping.xml                             # Runs tests by group (Sanity/Regression/etc.)
├── grid_docker.xml                          # Selenium Grid / Docker suite
├── run.bat                                  # Convenience script (mvn test)
├── pom.xml                                  # Maven configuration
├── logs/                                    # Log4j2 output
├── reports/                                 # ExtentReports HTML output
├── screenshots/                             # Failure screenshots
└── test-output/                             # TestNG default reports
```

---

## Prerequisites

Before running the tests, make sure you have:

- **Java JDK 21** installed and `JAVA_HOME` set
- **Maven** installed and on your `PATH`
- **Google Chrome** and/or **Microsoft Edge** browser
- A running **OpenCart** application to test against (by default the framework expects it at `http://localhost/opencart/`)
- *(Optional)* **Docker** and **Selenium Grid** if you want to run tests remotely

> Note: Selenium 4 has built-in driver management (Selenium Manager), so you usually do **not** need to download ChromeDriver/EdgeDriver manually.

---

## Setup

1. **Clone the repository**

   ```bash
   git clone https://github.com/Praveen12122k/OpenCart-UI-Automation-Framework.git
   cd OpenCart-UI-Automation-Framework
   ```

2. **Install dependencies**

   ```bash
   mvn clean install -DskipTests
   ```

3. **Point the framework at your OpenCart instance** by editing `src/test/resources/config.properties` (see next section).

---

## Configuration

All environment settings live in `src/test/resources/config.properties`:

```properties
appurl=http://localhost/opencart/
email=youremail@example.com
password=yourpassword
environment_env=local      # use "local" to run on this machine, "Remote" for Selenium Grid
gridurl=http://localhost:4444/wd/hub
```

- **`appurl`** — the URL of the OpenCart site under test.
- **`environment_env`** — set to `local` to launch a browser on your machine, or `Remote` to run against a Selenium Grid at `gridurl`.
- **`gridurl`** — the Selenium Grid / Docker hub endpoint (only used when `environment_env=Remote`).

Test data for the data-driven login test is stored in `testData/OpenCart_Logindata.xlsx` (columns: email, password, expected result — `Valid`/`Invalid`).

---

## How to Run the Tests

**Run the default suite (Maven):**

```bash
mvn test
```

By default, `pom.xml` runs `master.xml`, which executes the login test on Chrome.

**Run a specific TestNG suite file:**

```bash
mvn test -DsuiteXmlFile=crossbrowser.xml
```

Replace `crossbrowser.xml` with any suite from the [table below](#testng-suite-files).

**Run from the batch file (Windows):**

```bash
run.bat
```

> `run.bat` simply changes into the project directory and runs `mvn test`. Edit the path inside it to match your local folder.

---

## TestNG Suite Files

The project ships with several ready-made suites so you can run tests in different ways:

| File | What it does |
|---|---|
| `master.xml` | Default suite — runs the login test on Chrome. |
| `crossbrowser.xml` | Runs tests in **parallel** across Chrome and Edge. |
| `grouping.xml` | Runs tests filtered by TestNG **groups** (e.g. `Regression`, `Sanity`, `DataDriven`). |
| `grid_docker.xml` | Runs the suite on a **Selenium Grid / Docker** environment and attaches the ExtentReports listener. |

Tests are tagged with groups (`Sanity`, `Regression`, `Master`, `DataDriven`) so you can include/exclude them as needed.

---

## Reports, Logs & Screenshots

- **ExtentReports** — a styled HTML report is generated in the `reports/` folder after each run (e.g. `Test-Report-<timestamp>.html`). Open it in any browser.
- **TestNG reports** — default reports are written to `test-output/` (see `index.html` / `emailable-report.html`).
- **Logs** — execution logs are written to the `logs/` folder via Log4j2.
- **Screenshots** — when a test fails, a screenshot is captured automatically and saved (with a timestamp) in the `screenshots/` folder.

---

## Test Cases

| ID | Test | Description |
|---|---|---|
| **TC001** | Register Account | Registers a new user account with randomly generated data and verifies success. |
| **TC002** | Login Account | Logs in with valid credentials and verifies the "My Account" page is displayed. |
| **TC003** | Login Account (DDT) | Data-driven login using multiple rows from Excel; each row is marked `Valid` or `Invalid` and asserted accordingly. |

---

## Author

**Praveen** — [github.com/Praveen12122k](https://github.com/Praveen12122k)

> This project is a learning/portfolio framework demonstrating industry-standard UI test automation practices with Selenium, TestNG, and the Page Object Model.
