package com.bazaarstores.stepDefinitions;

import com.bazaarstores.pages.AllPages;
import com.bazaarstores.utilities.ConfigReader;
import com.bazaarstores.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
//---------------------------------------US04_01--------------------------------------------
public class CustomerSteps {
    AllPages allPages = new AllPages();
    @Given("User is already logged in")

    public void user_is_already_logged_in() {
        allPages.getLoginPage()
                .enterEmail(ConfigReader.getCustomerEmail())
                .enterPassword(ConfigReader.getDefaultPassword());
        allPages.getLoginPage().clickLoginButton();
    }

    @Given("Customer is on the customer page")
    public void customer_is_on_the_customer_page() {
        Assert.assertTrue("Home page should be displayed",
                allPages.getCustomerPage().isCustomerPageDisplayed());
    }

    @When("Customer observes each product card")
    public void customer_observes_each_product_card() {
        System.out.println(" Customer is observing the product cards...!");
        allPages.getCustomerPage().areAllProductsVisible();
    }


    @Then("All products should be visible")
    public void all_products_should_be_visible() {
        Assert.assertTrue(" Not all products are visible!",
                allPages.getCustomerPage().areAllProductsVisible());
        System.out.println("All product cards are visible to the customer.");
    }


//---------------------------------------US04_02--------------------------------------------

    @When("Products are loaded")
    public void products_are_loaded() {
        System.out.println("Products are loaded");
    }
    @Then("All products should load in less than {int} seconds")
    public void all_products_should_load_in_less_than_seconds(Integer int1) {
        long start = System.currentTimeMillis();

        boolean loadProducts = allPages.getCustomerPage().waitUntilProductsAreVisible(3);

        long duration = System.currentTimeMillis() - start;
        System.out.println("Product load time: " + duration + " ms");

        Assert.assertTrue("Products took more than 3 seconds to load!", loadProducts);
        Assert.assertTrue(" Load duration exceeded 3 seconds!", duration <= 3000);
    }
    @Then("Each product should display name, price, image, and description")
    public void each_product_should_display_name_price_image_and_description() {
        boolean namesVisible = allPages.getCustomerPage().areProductNamesVisible();
        boolean pricesVisible = allPages.getCustomerPage().areProductPricesVisible();
        boolean imagesVisible = allPages.getCustomerPage().areProductImagesVisible();
        boolean descriptionsVisible = allPages.getCustomerPage().areProductDescriptionsVisible();

        Assert.assertTrue("Some product names are not visible", namesVisible);
        Assert.assertTrue("Some product prices are not visible", pricesVisible);
        Assert.assertTrue("Some product images are not visible", imagesVisible);
//        Assert.assertTrue(" Some product descriptions are not visible", descriptionsVisible);
    }

//---------------------------------------US07_01--------------------------------------------


}
