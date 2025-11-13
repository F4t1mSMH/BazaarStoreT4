package com.bazaarstores.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty", // Generates readable console output with colored text
                "html:target/cucumber-reports/cucumber.html", // Creates HTML report at specified path
                "json:target/cucumber-reports/cucumber.json", // Generates JSON report for integration with other tools
                "junit:target/cucumber-reports/cucumber.xml", // Creates JUnit XML report for CI/CD systems
        },
        features = "src/test/resources/features",
        glue = "com.bazaarstores.stepDefinitions",
        tags =  " @US14_TC001 or  @US14_TC002 or  @US14_TC003 or  @US15_TC001 or @US15_TC002 or @US15_TC003 or @US16_TC001 or @CancelDeleteUser or @DeleteUser",
        dryRun = false
)
public class Admin {
}