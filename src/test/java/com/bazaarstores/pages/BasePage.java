package com.bazaarstores.pages;

import com.bazaarstores.utilities.ConfigReader;
import com.bazaarstores.utilities.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    protected WebDriverWait wait;
    protected Actions actions;

    public BasePage() {

        this.wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(ConfigReader.getExplicitWait()));
        this.actions = new Actions(Driver.getDriver());
    }

    // Helper method to find element
    protected WebElement findElement(By locator) {
        return Driver.getDriver().findElement(locator);
    }

    protected List<WebElement> findElements(By locator) {
        return Driver.getDriver().findElements(locator);
    }

    // Click Methods
    public void click(By locator) {
        waitForElementToBeClickable(locator);
        findElement(locator).click();
    }

    public void clickWithJS(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].click();", findElement(locator));
    }

    // Send Keys Methods
    public void sendKeys(By locator, String text) {
        waitForElementToBeVisible(locator);
        WebElement element = findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    public void sendKeysWithJS(By locator, String text) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].value='" + text + "';", findElement(locator));
    }

    // Wait Methods
    public void waitForElementToBeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementToBeClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitForUrlContains(String urlPart) {
        wait.until(ExpectedConditions.urlContains(urlPart));
    }

    // Get Text Methods
    public String getText(By locator) {
        waitForElementToBeVisible(locator);
        return findElement(locator).getText();
    }

    public String getAttribute(By locator, String attribute) {
        waitForElementToBeVisible(locator);
        return findElement(locator).getAttribute(attribute);
    }

    public String getValidationMessage(By locator) {
        return getAttribute(locator, "validationMessage");
    }

    // Boolean Methods
    public boolean isValidationMessageDisplayed(By locator) {
        String validationMessage = getValidationMessage(locator);
        return validationMessage != null && !validationMessage.isEmpty();
    }
    public boolean isDisplayed(By locator) {
        try {
            waitForElementToBeVisible(locator);
            return findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    public boolean isEnabled(By locator) {
        try {
            return findElement(locator).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSelected(By locator) {
        try {
            return findElement(locator).isSelected();
        } catch (Exception e) {
            return false;
        }
    }

    // Select Methods
    public void selectByVisibleText(By locator, String text) {
        waitForElementToBeVisible(locator);
        Select select = new Select(findElement(locator));
        select.selectByVisibleText(text);
    }

    public void selectByValue(By locator, String value) {
        waitForElementToBeVisible(locator);
        Select select = new Select(findElement(locator));
        select.selectByValue(value);
    }

    public void selectByIndex(By locator, int index) {
        waitForElementToBeVisible(locator);
        Select select = new Select(findElement(locator));
        select.selectByIndex(index);
    }

    // Action Methods
    public void hoverOver(By locator) {
        waitForElementToBeVisible(locator);
        actions.moveToElement(findElement(locator)).perform();
    }

    public void dragAndDrop(By sourceLocator, By targetLocator) {
        WebElement source = findElement(sourceLocator);
        WebElement target = findElement(targetLocator);
        actions.dragAndDrop(source, target).perform();
    }

    public void doubleClick(By locator) {
        waitForElementToBeClickable(locator);
        actions.doubleClick(findElement(locator)).perform();
    }

    public void rightClick(By locator) {
        waitForElementToBeClickable(locator);
        actions.contextClick(findElement(locator)).perform();
    }

    // JavaScript Methods
    public void scrollToElement(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", findElement(locator));
    }

    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scrollToTop() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.scrollTo(0, 0)");
    }

    // Alert Methods
    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        Driver.getDriver().switchTo().alert().accept();
    }

    public void dismissAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        Driver.getDriver().switchTo().alert().dismiss();
    }

    public String getAlertText() {
        wait.until(ExpectedConditions.alertIsPresent());
        return Driver.getDriver().switchTo().alert().getText();
    }

    // Window Methods
    public void switchToWindow(int windowIndex) {
        List<String> windows = Driver.getDriver().getWindowHandles().stream().toList();
        Driver.getDriver().switchTo().window(windows.get(windowIndex));
    }

    public void switchToFrame(By frameLocator) {
        Driver.getDriver().switchTo().frame(findElement(frameLocator));
    }

    public void switchToDefaultContent() {
        Driver.getDriver().switchTo().defaultContent();
    }

    // Navigation Methods
    public void navigateToUrl(String url) {
        Driver.getDriver().navigate().to(url);
    }

    public void refreshPage() {
        Driver.getDriver().navigate().refresh();
    }

    public void navigateBack() {
        Driver.getDriver().navigate().back();
    }

    public void navigateForward() {
        Driver.getDriver().navigate().forward();
    }

    // Screenshot Method
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}