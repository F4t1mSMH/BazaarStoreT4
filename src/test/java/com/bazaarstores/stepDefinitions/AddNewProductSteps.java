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
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AddNewProductSteps {
    private final ProductsPage productsPage = new ProductsPage();
    private final AddProductPage addProductPage = new AddProductPage();
    private final Faker faker = new Faker();
    private final MangerScenarioContext context;
    private Response response;

    public AddNewProductSteps(MangerScenarioContext context) {
        this.context = context;
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
                .get("/products/" +context.productId + "/");

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

    //Negative Add Steps
    @Given("a product exists in the system with the duplicate SKU")
    public void aProductExistsInTheSystemWithTheDuplicateSKU() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", "SKU Dupe Precondition");
        payload.put("price", 1.00);
        payload.put("stock", 1);
        payload.put("sku", MangerScenarioContext.DUPLICATE_SKU);
        payload.put("category_id", 1);
        payload.put("status", 1);

        given(ApiUtilities.spec())
                .body(payload)
                .when()
                .post("/products/create")
                .then()
                .statusCode(201);
        System.out.println("Precondition: Product created via API with SKU: " + MangerScenarioContext.DUPLICATE_SKU);
    }

    @And("the Store Manager enters product details and leave name empty")
    public void theStoreManagerEntersProductDetailsAndLeaveNameEmpty() {
        //no name
        addProductPage.enterProductPrice((int) Double.parseDouble(faker.commerce().price(500.00, 2000.00)))
                .enterProductStock(faker.number().numberBetween(10, 500))
                .enterProductSKU(faker.number().numberBetween(100000, 999999));
    }

    @And("the Store Manager enters product details and leave price empty")
    public void theStoreManagerEntersProductDetailsAndLeavePriceEmpty() {
        //no price
        addProductPage.enterProductName(faker.commerce().productName())
                .enterProductStock(faker.number().numberBetween(10, 500))
                .enterProductSKU(faker.number().numberBetween(100000, 999999));
    }

    @And("the Store Manager enters product details and leave stoke empty")
    public void theStoreManagerEntersProductDetailsAndLeaveStockEmpty() {
        // no stock
        addProductPage.enterProductName(faker.commerce().productName())
                .enterProductPrice((int) Double.parseDouble(faker.commerce().price(500.00, 2000.00)))
                .enterProductSKU(faker.number().numberBetween(100000, 999999));
    }


    @And("the Store Manager enters product details and leave sku empty")
    public void theStoreManagerEntersProductDetailsAndLeaveSkuEmpty() {
        //no sku
        addProductPage.enterProductName(faker.commerce().productName())
                .enterProductPrice((int) Double.parseDouble(faker.commerce().price(500.00, 2000.00)))
                .enterProductStock(faker.number().numberBetween(10, 500));
    }

    @And("the Store Manager enters product details and enter used sku")
    public void theStoreManagerEntersProductDetailsAndEnterUsedSku() {
        //used sku
        addProductPage.enterProductName(faker.commerce().productName())
                .enterProductPrice((int) Double.parseDouble(faker.commerce().price(500.00, 2000.00)))
                .enterProductStock(faker.number().numberBetween(10, 500))
                .enterProductSKU(Integer.parseInt(MangerScenarioContext.DUPLICATE_SKU)); // Using the constant
    }

}