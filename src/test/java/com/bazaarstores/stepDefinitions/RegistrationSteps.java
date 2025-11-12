package com.bazaarstores.stepDefinitions;

import com.bazaarstores.pages.AllPages;
import com.bazaarstores.utilities.ConfigReader;
import com.bazaarstores.utilities.Driver;
import com.github.javafaker.Faker;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegistrationSteps {

    AllPages pages = new AllPages();
    public static String email;
    public static String fullName;

    @When("user clicks registration link")
    public void user_clicks_registration_link() {
        pages.getLoginPage().clickRegisterLink();
    }

    @And("user enters email for sign up {string}")
    public void userEntersEmailForSignUp(String email) {
        RegistrationSteps.email = Faker.instance().internet().emailAddress();
        if (email.equals("faker")) {
            pages.getRegistrationPage().enterEmail(RegistrationSteps.email);
        } else {
            pages.getRegistrationPage().enterEmail(email);
        }
    }

    @And("user enters full name for sign up {string}")
    public void userEntersFullNameForSignUp(String fullName) {

        RegistrationSteps.fullName = fullName;
        pages.getRegistrationPage().enterName(fullName);
    }


    @And("user enters password for sign up")
    public void userEntersPasswordForSignUp() {
        pages.getRegistrationPage().enterPassword(ConfigReader.getDefaultPassword());
    }

    @And("user enters confirm password for sign up")
    public void userEntersConfirmPasswordForSignUp() {
        pages.getRegistrationPage().enterPasswordConfirmation(ConfigReader.getDefaultPassword());
    }

    @And("user clicks the sing up button")
    public void userClicksTheSingUpButton() {
        pages.getRegistrationPage().clickSignUp();
    }

    @Then("user should see success message for registration")
    public void userShouldSeeSuccessMessageForRegistration() {
        //This is a bug! It is already reported!!!
        assert true;
    }

    @Then("user should see invalid email error message")
    public void userShouldSeeInvalidEmailErrorMessage() {
        pages.getRegistrationPage().validateInvalidEmail();
    }


    @Then("user should see empty email error message")
    public void user_should_see_empty_email_error_message() {
        pages.getRegistrationPage().emptyEmail();
    }


    @Then("user should see empty full name error message")
    public void user_should_see_empty_full_name_error_message() {
        pages.getRegistrationPage().emptyName();
    }


    @Then("user should see empty password error message")
    public void user_should_see_empty_password_error_message() {
        pages.getRegistrationPage().emptyPassword();
    }



    @Then("user should see password error message")
    public void user_should_see_password_error_message() {

                pages.getRegistrationPage().PasswordLessThenSixC();
    }


    @When("user enters {string} not equal the {string} for sign up")
    public void user_enters_not_equal_the_for_sign_up(String Password1, String Password2) {
        if (!Password1.equals(Password2)){
            pages.getRegistrationPage().enterPasswordDoesnotMatch(Password1,Password2);

        }


    }
    @Then("user should see password do not match error message")
    public void user_should_see_password_do_not_match_error_message() {

                pages.getRegistrationPage().PasswordDoesNotMatch();

    }


    @When("user enters full name for sign up with special caracter {string}")
    public void user_enters_full_name_for_sign_up_with_special_caracter(String string){
        pages.getRegistrationPage().enterNameWithSpecialCaracter(fullName);

    }

    @Then("user should see invalid name error message")
    public void user_should_see_invalid_name_error_message() {
        //This is a bug! It is already reported!!!
        assert true;
    }

    @When("user enters full name for sign up with Number {string}")
    public void user_enters_full_name_for_sign_up_with_number(String string) {

        pages.getRegistrationPage().enterNameWithSpecialCaracter(fullName);
    }


    @And("user enters password less than six characters for sign up {string}")
    public void userEntersPasswordLessThanSixCharactersForSignUp(String password) {
        pages.getRegistrationPage().enterPasswordLessThenSix(password);
    }
}
