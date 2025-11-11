package com.bazaarstores.stepDefinitions;

import static io.restassured.RestAssured.given;
import com.bazaarstores.pages.AddProductPage;
import com.bazaarstores.pages.ProductsPage;
import com.bazaarstores.utilities.ApiUtilities;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;

public class AddNewProductByAPISteps {
    private final ProductsPage productsPage = new ProductsPage();
    private final AddProductPage addProductPage = new AddProductPage();
    private final Faker faker = new Faker();
    private final MangerScenarioContext context;
    private Response response;

    public AddNewProductByAPISteps() {
        this.context = new MangerScenarioContext();
    }
    @Given("a valid product payload is prepared")
    public void aValidProductPayloadIsPrepared() {
        String uniqueProductName = "API" + faker.commerce().productName();
        context.productToVerifyName = uniqueProductName;
        int priceValueInCents = ((int)Double.parseDouble(faker.commerce().price(10.00, 50.00)));
        int stockValue = faker.number().numberBetween(100, 500);
        String productSku = faker.regexify("[0-5]{3}[5-9]{3}");

        context.productPayload.put("name", uniqueProductName);
        context.productPayload.put("price", priceValueInCents);
        context.productPayload.put("sku", productSku);
        context.productPayload.put("stock", stockValue);
        context.productPayload.put("category_id", 1);
        //if I don't add them I get 500 code error
        context.productPayload.put("manufacturer", "Test Manufacturer Inc");
        context.productPayload.put("image_url", "https://example.com/test_image.jpg");
        context.productPayload.put("description", "A product created by API automation.");
        context.productPayload.put("discount", 0.0);

        System.out.println("Payload fields being sent: " + context.productPayload);
    }

    @When("a POST request is sent to create the product via API")
    public void aPOSTRequestIsSentToCreateTheProductViaAPI() {
        response = given(ApiUtilities.spec())
                .body(context.productPayload)
                .post("/products/create/");

        response.prettyPrint();
        response.then().statusCode(201);
        context.productId = response.jsonPath().getInt("product.id");
    }
    @Then("the product is created successfully and product ID is stored")
    public void theProductIsCreatedSuccessfullyAndProductIdIsStored() {
        Assert.assertTrue("Product ID was not retrieved after creation.", context.productId > 0);
        System.out.println("Product created via API with ID: " + context.productId);
    }

    @Then("the product can be retrieved successfully via API using its ID")
    public void theProductCanBeRetrievedSuccessfullyViaAPIUsingItsID() {
        Response response = given(ApiUtilities.spec())
                .when()
                .get("/products/" + context.productId);

        response.then().statusCode(200);
        String retrievedName = response.jsonPath().getString("name");
        Assert.assertEquals("Retrieved product name does not match the created product name.",
                context.productToVerifyName, retrievedName);
    }

    @When("the Store Manager searches for the newly created product by name")
    public void theStoreManagerSearchesForTheNewlyCreatedProductByName() {
        productsPage.searchProduct(context.productToVerifyName);
    }

    @Then("the product details should be visible on the Products page")
    public void theProductDetailsShouldBeVisibleOnTheProductsPage() {
        Assert.assertTrue("Product created via API is not visible on the UI list after searching.",
                productsPage.isProductDisplayed(context.productToVerifyName));
    }

    @And("the Store Manager enters a new product with name {string}, price {int}, stock {int}, sku {int}, and category {int}")
    public void theStoreManagerEntersANewProduct(String name, int price, int stock, int sku, int categoryId) {
        addProductPage.enterAllProductDetails(name, price, stock, sku, categoryId);
    }

}