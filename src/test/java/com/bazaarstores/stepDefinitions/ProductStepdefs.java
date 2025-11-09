package com.bazaarstores.stepDefinitions;

import com.bazaarstores.pages.*;
import com.bazaarstores.utilities.ConfigReader;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class ProductStepdefs {
    ProductsPage productsPage = new ProductsPage();
    AddProductPage addProductPage = new AddProductPage();
    AllPages pages = new AllPages();


    @Given("the store manger logged in on the dashboard page")
    public void theStoreMangerLoggedInOnTheDashboardPage() {
        pages.getLoginPage()
                .enterEmail(ConfigReader.getStoreManagerEmail())
                .enterPassword(ConfigReader.getDefaultPassword());
        pages.getLoginPage().clickLoginButton();
    }

    @And("the manger click on product page")
    public void theMangerClickOnProductPage() {
        pages.getDashboardPage().clickMangerProductsLink();
    }

    @When("the Store Manager clicks on the {string} button")
    public void theStoreManagerClicksOnTheButton(String addProduct) {
        if ("Add Product".equalsIgnoreCase(addProduct)) {
            productsPage.clickAddProductButton();
        } else {
            throw new IllegalArgumentException("Unsupported button name: " + addProduct);
        }
    }

    @And("the Store Manager enters product details")
    public void theStoreManagerEntersProductDetails() {
        addProductPage.enterProductName("camera")
                .enterProductPrice(1300)
                .enterProductStock(80)
                .enterProductSKU(909090);
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

        Assert.assertTrue("Product created successfully!" +
                        "Expected to contain: " + expectedMessage + ", Actual: " + actualMessage,
                actualMessage.contains(expectedMessage));
    }

    @And("the new product should appear in the product list")
    public void theNewProductShouldAppearInTheProductList() {
        Assert.assertTrue("The product 'camera' is not displayed in the product list table.",
                productsPage.isProductDisplayed("camera"));
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


    @And("the Store Manager enters product details and leave name empty")
    public void theStoreManagerEntersProductDetailsAndLeaveNameEmpty() {
        addProductPage.enterProductPrice(300)
                .enterProductStock(180)
                .enterProductSKU(12345);
    }

    @And("the Store Manager enters product details and leave price empty")
    public void theStoreManagerEntersProductDetailsAndLeavePriceEmpty() {
        addProductPage.enterProductName("HeadPhones")
                .enterProductStock(180)
                .enterProductSKU(12345);
    }

    @And("the Store Manager enters product details and leave stoke empty")
    public void theStoreManagerEntersProductDetailsAndLeaveStockEmpty() {
        addProductPage.enterProductName("HeadPhones")
                .enterProductPrice(300)
                .enterProductSKU(12345);
    }


    @And("the Store Manager enters product details and leave sku empty")
    public void theStoreManagerEntersProductDetailsAndLeaveSkuEmpty() {
        addProductPage.enterProductName("HeadPhones")
                .enterProductPrice(300)
                .enterProductStock(180);
    }

    @And("the Store Manager enters product details and enter used sku")
    public void theStoreManagerEntersProductDetailsAndEnterUsedSku() {
        addProductPage.enterProductName("HeadPhones")
                .enterProductPrice(300)
                .enterProductStock(180)
                .enterProductSKU(909090);

    }
}