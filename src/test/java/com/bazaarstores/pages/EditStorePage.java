package com.bazaarstores.pages;

import com.bazaarstores.utilities.ConfigReader;
import com.bazaarstores.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class EditStorePage extends BasePage  {


    private final By Name = By.id("first-name-column");
    private final By descriptionFrame = By.id("default_ifr");
    private final By descriptionBody = By.id("tinymce");
    private final By Submit = By.xpath("//button[@type='submit' or text()='Submit']");
    private final By storeHeader = By.xpath("//h3[normalize-space()='STORES']");

        public EditStorePage enterName(String newName) {
            sendKeys(Name, newName);
            return this;}


    public EditStorePage enterDescription(String text) {
            switchToFrame(descriptionFrame);

        WebElement textBox = findElement(descriptionBody);
        textBox.clear();
        if (text != null && !text.trim().isEmpty()) {
            textBox.sendKeys(text);
        }
        switchToDefaultContent();
        return this;
    }
    public StoresPage clickSave(){
        WebElement submitButton = findElement(Submit);
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", submitButton);
        js.executeScript("arguments[0].click();", submitButton);
        Assert.assertTrue( findElement(storeHeader).isDisplayed());
            return new StoresPage();
    }



    }





