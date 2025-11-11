package com.bazaarstores.stepDefinitions;

import static io.restassured.RestAssured.given;
import com.bazaarstores.pages.AddProductPage;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.bazaarstores.pages.ProductsPage;
import com.bazaarstores.utilities.ApiUtilities;
import io.restassured.response.Response;
import org.junit.Assert;

public class EditProductSteps {
    private final AddProductPage addProductPage = new AddProductPage();
    private final ProductsPage productsPage = new ProductsPage();
    private final Faker faker = new Faker();
    private Response response;

    @Given("edited product details are generated")
    public void editedProductDetailsAreGenerated() {
        String newProductName = faker.commerce().productName() + " (EDITED)";

        MangerScenarioContext.productPayload.put("name", newProductName);
        MangerScenarioContext.productPayload.put("price", 50.99);
        MangerScenarioContext.productToVerifyName = newProductName;

        System.out.println("Payload fields being sent for PUT: " + MangerScenarioContext.productPayload);
    }

    @When("a PUT request is sent to update the product via API")
    public void aPATCHRequestIsSentToUpdateTheProductViaAPI() {
        response = given(ApiUtilities.spec())
                .body(MangerScenarioContext.productPayload)
                .put("/products/" + MangerScenarioContext.productId);

        response.prettyPrint();
        response.then().statusCode(200);

        String updatedName = response.jsonPath().getString("product.name");

        Assert.assertEquals("The name in the API response does not match the expected edited name.",
                MangerScenarioContext.productToVerifyName, updatedName);
    }

    @Then("the product details should be updated successfully via API")
    public void theProductDetailsShouldBeUpdatedSuccessfullyViaAPI() {
        System.out.println("Product ID " + MangerScenarioContext.productId + " successfully updated via API.");
    }

    @And("the Store Manager edit product details")
    public void theStoreManagerEditProductDetails() {
        MangerScenarioContext.editedProductName = faker.commerce().productName();
        int newPrice = (int) Double.parseDouble(faker.commerce().price(500.00, 2000.00));
        int newStock = faker.number().numberBetween(10, 500);
        addProductPage.enterProductName(MangerScenarioContext.editedProductName)
                .enterProductPrice(newPrice)
                .enterProductStock(newStock);
    }

    @Then("the edited product should appear in the product list")
    public void theEditedProductShouldAppearInTheProductList() {
        Assert.assertTrue("The edited product '" + MangerScenarioContext.editedProductName + "' is not displayed in the product list table.",
                productsPage.isProductDisplayed(MangerScenarioContext.editedProductName));
    }

    //Negative Edit start

    @And("the Store Manager edit product details delete name")
    public void theStoreManagerEditProductDetailsDeleteName() {
        //edit and delete name
        int newPrice = (int) Double.parseDouble(faker.commerce().price(500.00, 2000.00));
        int newStock = faker.number().numberBetween(10, 500);
        addProductPage
                .enterProductName("")
                .enterProductPrice(newPrice)
                .enterProductStock(newStock);
    }

    @And("the Store Manager edit product details and delete price")
    public void theStoreManagerEditProductDetailsAndDeletePrice() {
        //edit and delete price
        int newStock = faker.number().numberBetween(10, 500);
        addProductPage
                .enterProductPrice(0)
                .enterProductStock(newStock);
    }

    @And("the Store Manager edit product details and delete stock")
    public void theStoreManagerEditProductDetailsAndDeleteStock() {
        //edit and delete stock
        int newPrice = (int) Double.parseDouble(faker.commerce().price(500.00, 2000.00));
        addProductPage
                .enterProductPrice(newPrice)
                .enterProductStock(0);
    }

    @And("the Store Manager edit product details and delete sku")
    public void theStoreManagerEditProductDetailsAndDeleteSku() {
        //edit and delete sku
        int newPrice = (int) Double.parseDouble(faker.commerce().price(500.00, 2000.00));
        addProductPage
                .enterProductPrice(newPrice)
                .enterProductSKU(0);
    }
    //Negative Edit end
}