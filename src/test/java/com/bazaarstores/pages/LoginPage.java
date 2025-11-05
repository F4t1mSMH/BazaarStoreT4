package com.bazaarstores.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    // By Locators
    private final By emailInput = By.cssSelector("input[name='email'], input[type='email'], input[placeholder*='Email']");
    private final By passwordInput = By.cssSelector("input[name='password'], input[type='password']");
    private final By loginButton = By.xpath("//button[.='Log in']");
    private final By signUp = By.linkText("Sign up");
    private final By errorMessage = By.xpath("//*[@class='toast-message']");
    private final By successMessage = By.cssSelector(".success, .success-message, [class*='success']");

    // Fluent Methods
    public LoginPage enterEmail(String email) {
        sendKeys(emailInput, email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        sendKeys(passwordInput, password);
        return this;
    }

    public DashboardPage clickLoginButton() {
        click(loginButton);
        return new DashboardPage();
    }

    public RegistrationPage clickRegisterLink() {
        click(signUp);
        return new RegistrationPage();
    }

    // Complete Login Method
    public DashboardPage login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        return clickLoginButton();
    }

    // Verification Methods
    public boolean isLoginPageDisplayed() {
        return isDisplayed(emailInput) && isDisplayed(passwordInput);
    }

    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorMessage);
    }

    public String getErrorMessageText() {
        return getText(errorMessage);
    }

    public boolean isSuccessMessageDisplayed() {
        return isDisplayed(successMessage);
    }

    public String getSuccessMessageText() {
        return getText(successMessage);
    }

    public boolean isValidationMessageDisplayed(String fieldName) {
        fieldName = fieldName.toLowerCase();
        By field = By.cssSelector("input[name='"+fieldName+"'], input[type='"+fieldName+"']");
        String validationMessage = getValidationMessage(field);
        return validationMessage != null && !validationMessage.isEmpty();
    }
}