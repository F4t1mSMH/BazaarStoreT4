package com.bazaarstores.pages;

import com.bazaarstores.utilities.ConfigReader;
import com.bazaarstores.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.bazaarstores.utilities.Driver.driver;

public class UsersPage extends BasePage {

        public UsersPage() {
            PageFactory.initElements(Driver.getDriver(), this);
        }

        private final By usersTable = By.xpath("//table[contains(@class,'table')]");
        private final By userRows = By.xpath("//table//tr[td]");
        private final By searchBox = By.xpath("//input[@type='text' and @name='email' and contains(@placeholder,'Search')]");
        private final By searchButton = By.xpath("//button[@type='submit' and contains(normalize-space(.),'Search')]");
        private final By noUserFoundMessage = By.xpath("//*[contains(text(),'No user')]");

        @FindBy(xpath = "//a[@href='https://bazaarstores.com/users/create']")
        public WebElement addUserButton;
        private final By nameInput = By.name("name");
        private final By emailInput = By.name("email");
        private final By roleSelect = By.name("role");
        private final By passwordInput = By.name("password");
        private final By confirmPasswordInput = By.name("password_confirmation");
        private final By submitButton = By.xpath("//button[contains(text(),'Submit')]");
        private final By successMessage = By.xpath("//*[contains(text(),'User added successfully')]");
        private final By updateSuccessMessage = By.xpath("//*[contains(text(),'User details updated successfully')]");
        private final By confirmDeleteButton = By.xpath("//button[contains(text(),'Confirm')]");
        private final By cancelDeleteButton = By.xpath("//button[contains(text(),'Cancel')]");
        @FindBy(css=".error-message") WebElement errorMessage;

        private final By editButtonInRow = By.xpath(".//button[contains(@class,'btn-outline-primary')]//i[contains(@class,'bi-pencil-square')]");
        private final By deleteButtonInRow = By.xpath(".//button[contains(text(),'Delete')]");



        public void goToUsersPage() {
            navigateToUrl(ConfigReader.getBaseUrl() + "/users");
            waitForElementToBeVisible(searchBox);
        }

        public void searchByEmail(String email) {
            waitForElementToBeVisible(searchBox);
            type(searchBox, email);
            click(searchButton);
            waitForElementToBeVisible(userRows);
        }

        public boolean isUsersTableDisplayed() {
            return isDisplayed(usersTable);
        }

        public int getUsersCount() {
            List<WebElement> rows = findElements(userRows);
            return rows.size();
        }

        public boolean isNoUserFoundMessageDisplayed() {
            return isDisplayed(noUserFoundMessage);
        }

        public boolean isEmailDisplayedInTable(String email) {
            List<WebElement> rows = findElements(userRows);
            for (WebElement row : rows) {
                if (row.getText().contains(email)) return true;
            }
            return false;
        }

        public boolean isRoleDisplayedForUser(String email, String role) {
            List<WebElement> rows = findElements(userRows);
            for (WebElement row : rows) {
                if (row.getText().contains(email) && row.getText().contains(role)) return true;
            }
            return false;
        }

        public void clickAddUser() {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(35));
            wait.until(ExpectedConditions.elementToBeClickable(addUserButton));
            ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", addUserButton);
        }

        public void fillUserForm(String name, String email, String role, String password, String confirmPassword) {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(35));
            wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));
            type(nameInput, name);
            type(emailInput, email);
            selectByVisibleText(roleSelect, role);
            type(passwordInput, password);
            type(confirmPasswordInput, confirmPassword);
        }

        public void submitUserForm() {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(30));
            WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
            ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", submitBtn);
        }

        // Waits & Verifications

        public void waitForUserTableUpdate() {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(usersTable));
        }

        public boolean waitForUserToAppear(String email, int timeoutInSeconds) {
            try {
                WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeoutInSeconds));
                By userRow = By.xpath("//table//td[text()='" + email + "']");
                return wait.until(ExpectedConditions.visibilityOfElementLocated(userRow)).isDisplayed();
            } catch (Exception e) {
                return false;
            }
        }

        public boolean isSuccessMessageDisplayed() {
            try {
                WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(30));
                wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        public boolean isUpdateSuccessMessageDisplayed() {
            return isDisplayed(updateSuccessMessage);
        }

        // Edit & Delete

        public void clickFirstEditButton() {
            WebElement editButton = Driver.getDriver().findElement(By.xpath("(//a[contains(@href,'/users/') and i[contains(@class,'bi-pencil-square')]])[1]"));
            editButton.click();
        }

        public void clearEmailField() {
            WebElement emailInput = Driver.getDriver().findElement(By.name("email"));
            emailInput.clear();
        }

        public void enterEmail(String email) {
            WebElement emailInput = Driver.getDriver().findElement(By.name("email"));
            emailInput.sendKeys(email);
        }

        public void clickSaveButton() {
            WebElement saveButton = Driver.getDriver().findElement(By.xpath("//button[contains(.,'Save') or @type='submit']"));
            saveButton.click();
        }


        public void selectRole(String roleName) {
            selectByVisibleText(roleSelect, roleName);
        }

        public void clickDeleteForUser(String email) {
            WebElement row = Driver.getDriver().findElement(By.xpath("//td[text()='" + email + "']/parent::tr"));
            WebElement deleteBtn = row.findElement(By.xpath(".//button[i[contains(@class,'bi-trash3')]]"));
            deleteBtn.click();
        }

        public void confirmDeletion() {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

            try {
                WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'Confirm') or contains(@class,'btn-primary')]")
                ));
                confirmBtn.click();
                System.out.println("Confirmed deletion button clicked successfully.");
            } catch (Exception e) {
                System.out.println("Confirm button not clickable: " + e.getMessage());
            }
        }

        public void cancelDeletion() {
            click(cancelDeleteButton);
        }

        public boolean isUserPresent(String email) {
            return isEmailDisplayedInTable(email);
        }

        public void verifyUserInList(String email) {
            searchByEmail(email);

            List<WebElement> rows = Driver.getDriver().findElements(userRows);
            boolean found = false;
            for (WebElement row : rows) {
                if (row.getText().contains(email)) {
                    found = true;
                    break;
                }
            }
            Assert.assertTrue("User should be present in the list", found);
        }
        public void waitForSuccessOrErrorMessage() {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(30));
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(successMessage),
                    ExpectedConditions.visibilityOf(errorMessage)
            ));
        }
        public String getEmailValidationMessage() {
            By validationLocator = By.xpath(
                    "//input[@name='email']/following-sibling::*[contains(@class,'error') or contains(@class,'invalid') or self::span or self::small]"
            );

            WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10));
            WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(validationLocator));
            return messageElement.getText().trim();
        }
        public boolean isErrorMessageDisplayed(String expectedMessage) {
            WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10));

            try {
                WebElement toastError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(@class,'toast-error') or contains(text(),'" + expectedMessage + "')]")
                ));
                if (toastError.getText().trim().contains(expectedMessage)) {
                    return true;
                }
            } catch (Exception ignored) {}

            try {
                WebElement inlineError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(@class,'error') or contains(@class,'invalid') or self::span or self::small][contains(text(),'" + expectedMessage + "')]")
                ));
                if (inlineError.getText().trim().contains(expectedMessage)) {
                    return true;
                }
            } catch (Exception ignored) {}

            return false;
        }
        public boolean waitForNoUserFoundMessage(int timeoutInSeconds) {
            try {
                WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeoutInSeconds));
                WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(noUserFoundMessage));
                return message.isDisplayed();
            } catch (Exception e) {
                System.out.println("No user found message not displayed: " + e.getMessage());
                return false;
            }
        }

    }