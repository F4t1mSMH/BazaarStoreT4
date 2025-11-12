package com.bazaarstores.stepDefinitions;

import com.bazaarstores.pages.AddProductPage;
import com.bazaarstores.pages.AllPages;
import com.bazaarstores.pages.DashboardPage;
import com.bazaarstores.pages.ProductsPage;
import com.bazaarstores.utilities.ConfigReader;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class MangerCommonSteps {
    private final ProductsPage productsPage = new ProductsPage();
    private final AddProductPage addProductPage = new AddProductPage();
    private final AllPages pages = new AllPages();
    private final DashboardPage dashboardPage = new DashboardPage();

    private final MangerScenarioContext context;

    public MangerCommonSteps() {
        this.context = new MangerScenarioContext();
    }

    // background start
    @Before("@Manager")
    @Given("the store Manager logged in on the dashboard page")
    public void theStoreMangerLoggedInOnTheDashboardPage() {
        pages.getLoginPage()
                .enterEmail(ConfigReader.getStoreManagerEmail())
                .enterPassword(ConfigReader.getDefaultPassword());
        pages.getLoginPage().clickLoginButton();
    }

    @And("the Manager click on product page")
    public void theMangerClickOnProductPage() {
        pages.getDashboardPage().clickMangerProductsLink();
        productsPage.waitForProductsPageToLoad();

    }
    // background end

    @When("the Store Manager clicks on the {string} button")
    public void theStoreManagerClicksOnTheButton(String buttonName) {
        if ("Add Product".equalsIgnoreCase(buttonName)) {
            productsPage.clickAddProductButton();
        } else {
            throw new IllegalArgumentException("Unsupported button name: " + buttonName);
        }
    }

    @And("the Store Manager submits the product form")
    public void theStoreManagerSubmitsTheProductForm() {
        addProductPage.clickSubmitButton();
    }

    @Then("a success message {string} should be displayed")
    public void aSuccessMessageShouldBeDisplayed(String expectedMessage) {
        Assert.assertTrue("Success message container must be displayed.",
                addProductPage.isSuccessMessageDisplayed());

        String actualMessage = addProductPage.getSuccessMessageText();
        Assert.assertTrue("The success message text does not contain the expected value. Actual: " + actualMessage,
                actualMessage.contains(expectedMessage));
    }

    @Then("an error message {string} should be displayed")
    public void anErrorMessageShouldBeDisplayed(String expectedMessage) {
        Assert.assertTrue("Error message container must be displayed.",
                addProductPage.isErrorMessageDisplayed());

        String actualMessage = addProductPage.getErrorMessageText();
        Assert.assertTrue("The error message text does not contain the expected value. " +
                        "Expected to contain: " + expectedMessage + ", Actual: " + actualMessage,
                actualMessage.contains(expectedMessage));
    }

    @And("the Store Manager refreshes the product list page")
    public void theStoreManagerRefreshesTheProductListPage() {
        productsPage.refreshPage();
    }

    @Then("a success message {string} should NOT be displayed")
    public void aSuccessMessageShouldNOTBeDisplayed(String expectedMessage) {
        boolean isMessageVisible = addProductPage.isSuccessMessageDisplayed();

        Assert.assertFalse(
                "The unexpected success message '" + expectedMessage + "' was displayed after API creation.",
                isMessageVisible
        );
    }


}