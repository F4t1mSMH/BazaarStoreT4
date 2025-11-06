package com.bazaarstores.pages;

import com.bazaarstores.utilities.Driver;
import com.bazaarstores.utilities.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class UsersPage extends BasePage {

    public UsersPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    // Locators
    private final By usersTable = By.xpath("//table[contains(@class,'table') or //table]");
    private final By userRows = By.xpath("//table//tr[td]");
    private final By searchBox = By.xpath("//input[@type='text' and @name='email' and contains(@placeholder,'Search')]");
    private final By searchButton = By.xpath("//button[@type='submit' and contains(normalize-space(.),'Search')]");
    private final By noUserFoundMessage = By.xpath("//*[normalize-space(text())='No user found' or contains(text(),'No user')]");

    // Actions
    public void goToUsersPage() {
        navigateToUrl(ConfigReader.getBaseUrl() + "/users");
        waitForElementToBeVisible(searchBox);
    }

    public boolean isUsersTableDisplayed() {
        return isDisplayed(usersTable);
    }

    public int getUsersCount() {
        List<WebElement> rows = findElements(userRows);
        return rows.size();
    }

    public void searchByEmail(String email) {
        waitForElementToBeVisible(searchBox);
        findElement(searchBox).clear();
        findElement(searchBox).sendKeys(email);
        click(searchButton);

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(driver -> isDisplayed(userRows) || isDisplayed(noUserFoundMessage));
    }

    public boolean isNoUserFoundMessageDisplayed() {
        return isDisplayed(noUserFoundMessage);
    }

    public boolean isEmailDisplayedInTable(String email) {
        List<WebElement> rows = findElements(userRows);
        for (WebElement row : rows) {
            if (row.getText().contains(email)) {
                return true;
            }
        }
        return false;
    }
}
