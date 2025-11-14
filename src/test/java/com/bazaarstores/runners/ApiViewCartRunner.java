package com.bazaarstores.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json",
                "junit:target/cucumber.xml"
        },
         features = "src/test/resources/features",
        glue = "com/bazaarstores/stepDefinitions",
        tags = "@CustomerCartAPI",
        dryRun = false,
        monochrome = true,
        publish = true
)
public class ApiViewCartRunner {
}
