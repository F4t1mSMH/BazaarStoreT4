package com.bazaarstores.stepDefinitions;

import com.bazaarstores.utilities.ConfigReader;
import com.bazaarstores.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

//    @Before
//    public void setUp(Scenario scenario) {
//        System.out.println("Starting scenario: " + scenario.getName());
//        Driver.getDriver().get(ConfigReader.getBaseUrl());
//    }

    @After
    public void tearDown(Scenario scenario) {
        // Take screenshot on failure
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) Driver.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failed Screenshot");
        }
        
        System.out.println("Finished scenario: " + scenario.getName() + 
                          " - Status: " + scenario.getStatus());
        
        Driver.quitDriver();
    }
}