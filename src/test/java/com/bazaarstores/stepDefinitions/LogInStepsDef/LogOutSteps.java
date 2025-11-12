package com.bazaarstores.stepDefinitions.LogInStepsDef;

import com.bazaarstores.pages.AllPages;
import com.bazaarstores.utilities.ConfigReader;
import com.bazaarstores.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LogOutSteps {

    AllPages pages = new AllPages();

    @Given("user goes to dashboard page")
    public void user_goes_to_dashboard_page() {
        Driver.getDriver().get(ConfigReader.getBaseUrl());
        pages.getLoginPage()
                .login(ConfigReader.getCustomerEmail(), ConfigReader.getDefaultPassword());
    }

    @When("the user moves the cursor to the user icon")
    public void the_user_moves_the_cursor_to_the_user_icon() {
        pages.getDashboardPage().movetousericon();

    }

    @When("the user moves the cursor to the Log out Button")
    public void the_user_moves_the_cursor_to_the_log_out_button() {
        pages.getDashboardPage().movetologoutButton();
    }

    @When("the user clicks the logout button")
    public void the_user_clicks_the_logout_button() {
        pages.getDashboardPage().clickLogout();
    }

    @Then("the user should be logged out successfully")
    public void the_user_should_be_logged_out_successfully() {
        pages.getLoginPage().isLoginPageDisplayed();
    }


    @Then("Verify user is redirected to login page after logging out")
    public void verify_user_is_redirected_to_login_page_after_logging_out() {
      Driver.getDriver().getCurrentUrl().contains("Log in");
    }
}