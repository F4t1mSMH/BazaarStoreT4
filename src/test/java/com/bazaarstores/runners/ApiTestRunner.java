package com.bazaarstores.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.bazaarstores.stepDefinitions",
        tags = "@API",
        plugin = {
                "pretty", // Generates readable console output with colored text
                "html:target/cucumber-reports/cucumber.html", // Creates HTML report at specified path
                "json:target/cucumber-reports/cucumber.json", // Generates JSON report for integration with other tools
                "junit:target/cucumber-reports/cucumber.xml", // Creates JUnit XML report for CI/CD systems
        },
        monochrome = true
)
public class ApiTestRunner {
}