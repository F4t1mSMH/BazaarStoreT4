package com.bazaarstores.stepDefinitions;

import com.bazaarstores.pages.AllPages;
import com.bazaarstores.pages.UsersPage;
import com.bazaarstores.utilities.ConfigReader;
import com.bazaarstores.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class UsersStepDefs {

    AllPages pages = new AllPages();
    UsersPage usersPage = new UsersPage();

    @Given("the admin is logged into the Bazaar Admin Panel")
    public void the_admin_is_logged_into_the_bazaar_admin_panel() {
        Driver.getDriver().get(ConfigReader.getBaseUrl() + "/login");
        pages.getLoginPage().login(ConfigReader.getAdminEmail(), ConfigReader.getDefaultPassword());
        Assert.assertTrue("Dashboard should be visible after login",
                pages.getDashboardPage().isProfileVisitChartDisplayed());
    }

    @When("the admin navigates to the Users management page")
    public void the_admin_navigates_to_the_users_management_page() {
        usersPage.goToUsersPage();
    }

    @Then("a list of all registered users is displayed with correct details")
    public void a_list_of_all_registered_users_is_displayed_with_correct_details() {
        Assert.assertTrue("Users table should be displayed", usersPage.isUsersTableDisplayed());
        Assert.assertTrue("There should be at least one user row", usersPage.getUsersCount() > 0);
    }

    @And("the admin searches for {string}")
    public void the_admin_searches_for(String email) {
        usersPage.searchByEmail(email);
    }

    @Then("the user {string} appears in the search results")
    public void the_user_appears_in_the_search_results(String email) {
        Assert.assertTrue("Expected to find user with email: " + email,
                usersPage.isEmailDisplayedInTable(email));
    }

    @Then("the message {string} is displayed")
    public void the_message_is_displayed(String expectedMessage) {
        Assert.assertTrue("Expected message to be displayed: " + expectedMessage,
                usersPage.isNoUserFoundMessageDisplayed());
    }
}
