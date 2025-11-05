package com.bazaarstores.pages;


import com.bazaarstores.utilities.Driver;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;

public class RegistrationPage extends BasePage {

    private final By email = By.name("email");
    private final By name = By.name("name");
    private final By password = By.name("password");
    private final By password_confirmation = By.name("password_confirmation");
    private final By signUp = By.xpath("//button[.='Sign Up']");
    private final By invalidEmailMessage = By.xpath("//li[.='The email field must be a valid email address.']");

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


}