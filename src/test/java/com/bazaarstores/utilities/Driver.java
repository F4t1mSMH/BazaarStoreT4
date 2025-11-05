package com.bazaarstores.utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class Driver {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            String browser = ConfigReader.getBrowser().toLowerCase();
            
            switch (browser) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (ConfigReader.isHeadless()) {
                        chromeOptions.addArguments("--headless");
                    }
                    chromeOptions.addArguments("--start-maximized");
                    chromeOptions.addArguments("--disable-notifications");
                    driver.set(new ChromeDriver(chromeOptions));
                    break;
                    
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (ConfigReader.isHeadless()) {
                        firefoxOptions.addArguments("--headless");
                    }
                    driver.set(new FirefoxDriver(firefoxOptions));
                    break;
                    
                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    if (ConfigReader.isHeadless()) {
                        edgeOptions.addArguments("--headless");
                    }
                    driver.set(new EdgeDriver(edgeOptions));
                    break;
                    
                default:
                    throw new IllegalArgumentException("Browser not supported: " + browser);
            }
            
            driver.get().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
            driver.get().manage().timeouts()
                .pageLoadTimeout(Duration.ofSeconds(ConfigReader.getPageLoadTimeout()));
            driver.get().manage().window().maximize();
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}