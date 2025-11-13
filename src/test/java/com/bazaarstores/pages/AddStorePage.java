package com.bazaarstores.pages;

import com.bazaarstores.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddStorePage extends BasePage {
    private final By Name = By.id("first-name-column");
    private final By Location = By.id("location-id-column");
    private final By Admins = By.id("admin-column");
    private final By descriptionFrame = By.id("default_ifr");
    private final By descriptionBody = By.id("tinymce");
    private final By Submit = By.xpath("//button[@type='submit' or text()='Submit']");
    private final By errorMessage = By.cssSelector("div.alert.alert-danger li");
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));


    public AddStorePage enterName(String name) {
        WebElement nameField = Driver.getDriver().findElement(Name);
        nameField.clear();
        if (name != null && !name.trim().isEmpty()) {
            nameField.sendKeys(name);
        }
        return this;
    }


    public AddStorePage enterLocation(String location) {
        WebElement locationField = Driver.getDriver().findElement(Location);
        locationField.clear();
        if (location != null && !location.isEmpty()) {
            locationField.sendKeys(location);
        }
        return this;
    }


    public AddStorePage enterAdmins(String admin) {
        try {

            if (admin == null || admin.trim().isEmpty()) {
                System.out.println("Admin value is empty — skipping selection.");
                return this;
            }
            selectByVisibleText(this.Admins,admin);
        } catch (NoSuchElementException e) {
            System.out.println("Option not found for admin: " + admin);
        }

        return this;
    }


    public AddStorePage enterDescription(String text) {
        Driver.getDriver().switchTo().frame(Driver.getDriver().findElement(descriptionFrame));
        WebElement textBox = Driver.getDriver().findElement(descriptionBody);
        textBox.clear();
        if (text != null && !text.trim().isEmpty()) {
            textBox.sendKeys(text);
        }
        Driver.getDriver().switchTo().defaultContent();
        return this;
    }


    public StoresPage clickSubmit() {

        click(Submit);
        return new StoresPage();
    }
    public String getErrorMessage() {
        WebElement error = Driver.getDriver().findElement(errorMessage);
        return error.getText().trim();
    }




}
