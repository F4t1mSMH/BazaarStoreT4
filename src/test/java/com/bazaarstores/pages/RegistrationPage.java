package com.bazaarstores.pages;


import com.bazaarstores.utilities.Driver;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegistrationPage extends BasePage {


    Faker faker = new Faker();

    private final By email = By.name("email");
    private final By name = By.name("name");
    private final By password = By.name("password");
    private final By password_confirmation = By.name("password_confirmation");
    private final By signUp = By.xpath("//button[.='Sign Up']");
    private final By invalidEmailMessage = By.xpath("//li[.='The email field must be a valid email address.']");
    private final By emptyEmailMessage = By.xpath("//li[.='The email field is required.']");
    private final By emptyNameMessage = By.xpath("//li[.='The name field is required.']");
    private final By emptyPasswordMessage = By.xpath("//li[.='The password field is required.']");
    private final By PasswordLTSMessage = By.xpath("//*[.='The password field must be at least 6 characters.']");
    private final By PasswordDNMatchMessage = By.xpath("//*[.='The password field confirmation does not match.']");

    public RegistrationPage enterEmail(String email) {
        Driver.getDriver().findElement(this.email).sendKeys(email);
        return this;
    }

    public RegistrationPage enterName(String name) {
        Driver.getDriver().findElement(this.name).sendKeys(name);
        return this;
    }

    public RegistrationPage enterPassword(String password) {
        Driver.getDriver().findElement(this.password).sendKeys(password);
        return this;
    }

    public RegistrationPage enterPasswordConfirmation(String confirmPassword) {
        Driver.getDriver().findElement(this.password_confirmation).sendKeys(confirmPassword);
        return this;
    }

    public RegistrationPage clickSignUp() {
        Driver.getDriver().findElement(signUp).click();
        return this;
    }

    public RegistrationPage validateInvalidEmail() {
        assertEquals(
                "The email field must be a valid email address.",
                Driver.getDriver().findElement(invalidEmailMessage).getText()
        );
        return this;
    }

    public RegistrationPage emptyEmail() {
        assertTrue(
                Driver.getDriver().findElement(emptyEmailMessage).getText().contains("The email field is required")
        );
        return this;
    }

    public RegistrationPage emptyName() {
        assertTrue(Driver.getDriver().findElement(emptyNameMessage).getText().contains("name field is required"));
        return this;
    }

    public RegistrationPage emptyPassword() {
        assertEquals(
                "The password field is required.",
                Driver.getDriver().findElement(emptyPasswordMessage).getText()
        );
        return this;
    }

    public RegistrationPage enterPasswordLessThenSix(String passwordLessthansix) {
        Driver.getDriver().findElement(this.password).sendKeys(passwordLessthansix);
        return this;
    }

    public RegistrationPage PasswordLessThenSixC() {
        assertEquals(
                "The password field must be at least 6 characters.",
                Driver.getDriver().findElement(PasswordLTSMessage).getText()
        );
        return this;
    }


    public RegistrationPage enterPasswordDoesnotMatch(String password,String ConfirmPassword) {
        Driver.getDriver().findElement(this.password).sendKeys(password);
        Driver.getDriver().findElement(this.password_confirmation).sendKeys(ConfirmPassword);
        return this;
    }


    public RegistrationPage PasswordDoesNotMatch() {
        assertEquals(
                "The password field confirmation does not match.",
                Driver.getDriver().findElement(PasswordDNMatchMessage).getText()
        );
        return this;
    }

    public RegistrationPage enterNameWithSpecialCaracter(String name) {
        String finalName = name+"@";
                Driver.getDriver().findElement(this.name).sendKeys(finalName);
        return this;
    }

    public RegistrationPage enterNameWithNumber(String name) {
        String finalName = name+"1";
        Driver.getDriver().findElement(this.name).sendKeys(finalName);
        return this;
    }

}