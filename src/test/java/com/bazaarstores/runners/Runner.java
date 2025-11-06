package com.bazaarstores.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "junit:target/cucumber-reports/cucumber.xml"
        },
        features = "src/test/resources/features",
        glue = "com.bazaarstores.stepDefinitions",
        tags ="@US14_TC001 or @US14_TC002 or  @US14_TC003" ,
        dryRun = false
)
public class Runner {
}
