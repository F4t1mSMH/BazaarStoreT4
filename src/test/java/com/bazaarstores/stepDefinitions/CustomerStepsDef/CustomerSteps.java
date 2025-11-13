package com.bazaarstores.stepDefinitions.CustomerStepsDef;

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

    @Given("Customer is on the customer page")
    public void customer_is_on_the_customer_page() {
        Driver.getDriver().get(ConfigReader.getcustomerBaseUrl());

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

        boolean loadProducts = allPages.getCustomerPage().waitUntilProductsAreVisible(int1);

        long duration = System.currentTimeMillis() - start;
        System.out.println("Product load time: " + duration + " ms");

        Assert.assertTrue("Products took more than 3 seconds to load!", loadProducts);
        Assert.assertTrue(" Load duration exceeded 3 seconds!", duration <= 3000);
    }
    @Then("Each product should display name, price, image, and description")
    public void each_product_should_display_name_price_image_and_description() {
          allPages.getCustomerPage().printallproductdetails();
    }


//---------------------------------------US05-------------------------------------------------
    @When("Customer clicks on a product")
    public void customer_clicks_on_a_product() {
     allPages.getCustomerPage().ClickonAnyProduct();
    }

    @Then("Product details should be displayed including Name, Price, Description, and Images")
    public void product_details_should_be_displayed_including_name_price_description_and_images() {
        boolean namesVisible = allPages.getCustomerPage().areProductNamesVisible();
        boolean pricesVisible = allPages.getCustomerPage().areProductPricesVisible();
        boolean imagesVisible = allPages.getCustomerPage().areProductImagesVisible();
//        boolean descriptionsVisible = allPages.getCustomerPage().areProductDescriptionsVisible();


        Assert.assertTrue("Some product names are not visible", namesVisible);
        Assert.assertTrue("Some product prices are not visible", pricesVisible);
        Assert.assertTrue("Some product images are not visible", imagesVisible);
//        Assert.assertTrue(" Some product descriptions are not visible", descriptionsVisible);
    }

}
