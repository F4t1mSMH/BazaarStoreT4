package com.bazaarstores.stepDefinitions;

import com.bazaarstores.pages.AddProductPage;
import com.bazaarstores.pages.ProductsPage;
import com.bazaarstores.utilities.ApiUtilities;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AddNewProductSteps {
    private final ProductsPage productsPage = new ProductsPage();
    private final AddProductPage addProductPage = new AddProductPage();
    private final Faker faker = new Faker();

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
                .enterProductSKU(Integer.parseInt(MangerScenarioContext.DUPLICATE_SKU));
    }
}
