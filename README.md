# Bazaar Stores Test Automation Framework

##  Framework Overview

This is a **Selenium + Cucumber + Java + Maven** test automation framework with:
- **Fluent Page Object Model** design pattern
- **BasePage** with reusable methods
- **AllPages** class for centralized page object management
- **Parallel execution** support
- **Multiple browser** support (Chrome, Firefox, Edge)
- **REST Assured** integration for API testing
- **ConfigReader** for configuration management
- **Cucumber BDD** for readable test scenarios

---

##  Project Structure

```
test-automation/
├── src/
│   ├── main/java/
│   └── test/
│       ├── java/
│       │   └── com/bazaarstores/
│       │       ├── pages/
│       │       │   ├── BasePage.java
│       │       │   ├── AllPages.java
│       │       │   ├── LoginPage.java
│       │       │   ├── RegistrationPage.java
│       │       │   └── DashboardPage.java
│       │       ├── stepDefinitions/
│       │       │   ├── Hooks.java
│       │       │   └── LoginSteps.java
│       │       ├── runners/
│       │       │   ├── TestRunner.java
│       │       │   ├── SmokeTestRunner.java
│       │       │   └── ApiTestRunner.java
│       │       └── utilities/
│       │           ├── ConfigReader.java
│       │           ├── DriverManager.java
│       │           └── ApiUtil.java
│       └── resources/
│           ├── features/
│               └── Login.feature
│          
├── target/
│   ├── cucumber-reports/
│   └── screenshots/
├── pom.xml
└── configuration.properties
```

---

##  Getting Started

### Prerequisites
- Java 21 or higher
- Maven 3.6+
- IDE (IntelliJ IDEA)

### Setup
1. Clone or download the framework
2. Import as Maven project
3. Update `configuration.properties` if needed
4. Run `mvn clean install` to download dependencies

---

##  Maven Commands

### Execute All Tests
```bash
mvn clean test
```

### Execute Specific Runner
```bash
# Smoke Tests
mvn clean test -Dtest=SmokeTestRunner

# API Tests
mvn clean test -Dtest=ApiTestRunner

# Regression Tests
mvn clean test -Dtest=TestRunner
```

### Execute with Different Browsers
```bash
# Chrome (default)
mvn clean test -Dbrowser=chrome

# Firefox
mvn clean test -Dbrowser=firefox

# Edge
mvn clean test -Dbrowser=edge

# Headless Chrome
mvn clean test -Dbrowser=chrome -Dheadless=true
```

### Execute with Tags
```bash
# Run Smoke tests
mvn clean test -Dcucumber.filter.tags="@Smoke"

# Run Customer tests
mvn clean test -Dcucumber.filter.tags="@Customer"

# Run Admin tests
mvn clean test -Dcucumber.filter.tags="@Admin"

# Run API tests
mvn clean test -Dcucumber.filter.tags="@API"

# Run multiple tags
mvn clean test -Dcucumber.filter.tags="@Smoke and @Customer"

# Exclude tags
mvn clean test -Dcucumber.filter.tags="@Regression and not @API"
```

### Combination Commands
```bash
# Run Smoke tests in Firefox
mvn clean test -Dtest=SmokeTestRunner -Dbrowser=firefox

# Run specific tags with browser
mvn clean test -Dcucumber.filter.tags="@Login" -Dbrowser=chrome

# Run in headless mode with specific runner
mvn clean test -Dtest=SmokeTestRunner -Dbrowser=chrome -Dheadless=true
```

### Parallel Execution
```bash
# Execute with 3 threads
mvn clean test -DthreadCount=3

# Execute with 5 threads
mvn clean test -DthreadCount=5
```

---

##  Available Tags

| Tag | Description |
|-----|-------------|
| `@Regression` | All regression tests |
| `@Smoke` | Critical smoke tests |
| `@Login` | Login functionality tests |
| `@Customer` | Customer role tests |
| `@Admin` | Admin role tests |
| `@StoreManager` | Store Manager role tests |
| `@API` | API tests using REST Assured |
| `@Negative` | Negative test scenarios |

---

##  Framework Features

### 1. BasePage Class
Contains reusable methods:
- Click methods (click, clickWithJS)
- Send keys methods
- Wait methods (explicit waits)
- Verification methods (isDisplayed, isEnabled)
- Select dropdown methods
- Action methods (hover, drag-drop, double-click)
- JavaScript executor methods
- Alert handling
- Window/Frame switching
- Screenshot capture

### 2. AllPages Class
Centralized page object management with getter methods:
```java
AllPages allPages = new AllPages(driver);
allPages.getLoginPage().enterEmail("test@test.com");
allPages.getDashboardPage().isDashboardPageDisplayed();
```

### 3. Fluent Page Object Model
Chain methods for readable test code:
```java
allPages.getLoginPage()
    .enterEmail("customer@sda.com")
    .enterPassword("Password.12345")
    .clickLoginButton();
```

### 4. REST Assured Integration
Use API calls within UI tests:
```java
@When("user logs in via API with valid credentials")
public void user_logs_in_via_api_with_valid_credentials() {
    String token = ApiUtil.loginAndGetToken(email, password);
    // Continue with UI test
}
```

### 5. Configuration Management
All test data in `configuration.properties`:
```properties
base.url=https://bazaarstores.com
customer.email=customer@sda.com
admin.email=admin@sda.com
default.password=Password.12345
```

Access via ConfigReader:
```java
String email = ConfigReader.getCustomerEmail();
String password = ConfigReader.getDefaultPassword();
```

---

##  Writing New Tests

### 1. Create Feature File
```gherkin
@YourTag
Feature: Your Feature Name

  Scenario: Your test scenario
    Given user is on some page
    When user performs some action
    Then user should see expected result
```

### 2. Create Step Definitions
```java
public class YourSteps {
    AllPages allPages = new AllPages(DriverManager.getDriver());
    
    @Given("user is on some page")
    public void user_is_on_some_page() {
        // Your implementation
    }
}
```

### 3. Create Page Object (if needed)
```java
public class YourPage extends BasePage {
    private final By elementLocator = By.cssSelector("elementLocator");

    
    public YourPage(WebDriver driver) {
        super(driver);
    }
    
    public YourPage enterData(String data) {
        sendKeys(elementLocator, data);
        return this;
    }
}
```

### 4. Add to AllPages
```java
public class AllPages {
    private YourPage yourPage;
    
    public YourPage getYourPage() {
        if (yourPage == null) {
            yourPage = new YourPage(driver);
        }
        return yourPage;
    }
}
```

---

##  Test Reports

After execution, reports are generated in:
- **HTML Report**: `target/cucumber-reports/cucumber.html`
- **JSON Report**: `target/cucumber-reports/cucumber.json`
- **XML Report**: `target/cucumber-reports/cucumber.xml`
- **Screenshots**: `target/screenshots/` (on failure)

---

##  Configuration

### Browser Configuration
Edit `configuration.properties`:
```properties
browser=chrome          # chrome, firefox, edge
headless=false         # true for headless mode
```

### Timeout Configuration
```properties
implicit.wait=10       # Implicit wait in seconds
explicit.wait=15       # Explicit wait in seconds
page.load.timeout=30   # Page load timeout
```

### User Roles
```properties
customer.email=customer@sda.com
admin.email=admin@sda.com
storemanager.email=storemanager@sda.com
default.password=Password.12345
```

---

##  Example Test Execution Flow

1. **Start**: Maven command triggers runner
2. **@Before Hook**: Initialize driver, navigate to base URL
3. **Feature Execution**: Run scenarios based on tags
4. **Step Definitions**: Execute test steps using AllPages
5. **Page Objects**: Interact with UI using BasePage methods
6. **API Verification**: Optional REST Assured calls
7. **@After Hook**: Take screenshot on failure, quit driver
8. **Report Generation**: Create HTML/JSON/XML reports

---

##  Contributing

### Adding New Features
1. Create feature file in `src/test/resources/features/`
2. Create step definitions in `stepDefinitions` package
3. Create page objects in `pages` package extending BasePage
4. Add page getter to AllPages class
5. Update configuration if needed

### Best Practices
- Use fluent design pattern for page objects
- Keep step definitions clean and readable
- Use ConfigReader for all test data
- Use BasePage methods instead of raw Selenium
- Add meaningful tags to scenarios
- Write descriptive scenario names
- Use AllPages for centralized page management

---

##  Support & Resources

- **Application URL**: https://bazaarstores.com/
- **Swagger API**: https://bazaarstores.com/api/documentation
- **Framework**: Selenium 4.15.0 + Cucumber 7.14.0
- **Build Tool**: Maven
- **Language**: Java 21

---

##  General Notes

This framework demonstrates:
1. **Industry-standard** project structure
2. **Page Object Model** with fluent design
3. **BDD** approach with Cucumber
4. **Parallel execution** capabilities
5. **Cross-browser** testing
6. **API + UI** hybrid testing
7. **Centralized configuration** management
8. **Reusable components** (BasePage, AllPages)

You can:
- Add new test scenarios easily
- Practice BDD writing
- Learn POM design pattern
- Understand fluent interfaces
- Execute tests with various configurations
- Generate professional reports