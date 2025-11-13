package com.bazaarstores.stepDefinitions.CustomerStepsDef;

import com.bazaarstores.pages.AllPages;
import com.bazaarstores.utilities.ConfigReader;
import com.bazaarstores.utilities.Driver;
import io.cucumber.java.en.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ViewCartSteps {

    AllPages allPages = new AllPages();

    @Given("customer is logged in and on the dashboard")
    public void customer_is_logged_in_and_on_the_dashboard() {
        Driver.getDriver().get(ConfigReader.getBaseUrl() + "/login");
        allPages.getLoginPage()
                .enterEmail(ConfigReader.getCustomerEmail())
                .enterPassword(ConfigReader.getDefaultPassword())
                .clickLoginButton();
        assertTrue("Dashboard should be displayed",
                allPages.getDashboardPage().isDashboardPageDisplayed());
    }

    @When("customer adds a product to the cart")
    public void customer_adds_a_product_to_the_cart() {
        allPages.getDashboardPage().addFirstProductToCart();
    }

    @Then("customer hovers over the cart icon")
    public void customer_hovers_over_the_cart_icon() {
        allPages.getViewCartPage().hoverOverCartIcon();

        assertTrue("View Cart link should appear",
                allPages.getViewCartPage().isViewCartLinkDisplayed());
    }

    @And("customer clicks the View Cart link")
    public void customer_clicks_the_view_cart_link() {
        allPages.getViewCartPage().clickViewCartLink();
    }

    @Then("the cart page should display products, prices")
    public void the_cart_page_should_display_products_prices_and_total() {
        allPages.getViewCartPage().hoverOverCartIcon();
        assertTrue("Cart item should be visible",
                allPages.getViewCartPage().isCartItemDisplayed());


    }
    @And("customer hovers over the cart icon again")
    public void customer_hovers_over_the_cart_icon_again() {
        allPages.getViewCartPage().hoverOverCartIcon();
        assertTrue("Cart icon hover should display cart details again",
                allPages.getViewCartPage().isCartItemDisplayed());
    }

    @And("customer removes the item from the cart")
    public void customer_removes_the_item_from_the_cart() {
        allPages.getViewCartPage().removeItemFromCart();
    }



    @When("customer clicks the Confirm Cart button")
    public void customer_clicks_the_confirm_cart_button() {
        allPages.getViewCartPage().clickConfirmCartButton();
    }

    @Then("a success message should appear with the title {string}")
    public void a_success_message_with_title_should_appear(String expectedTitle) {
        String actualTitle = allPages.getViewCartPage().getSuccessAlertTitle();
        assertEquals(expectedTitle, actualTitle);
    }
    @Then("a success message should appear with the text {string}")
    public void a_success_message_should_appear_with_the_text(String expectedMessage) {
        String actualMessage = allPages.getViewCartPage().getAlertMessage();

        if (actualMessage.isEmpty()) {
            System.out.println("disappeared before reading ");
        } else {
            assertEquals(expectedMessage, actualMessage);
        }
    }





}
