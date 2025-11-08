package com.bazaarstores.pages.MangerPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddProductPage extends ProductsPage {
    private final By productNameInput = By.name("name");
    private final By productPriceInput = By.name("price");
    private final By productStockInput = By.name("stock");
    private final By productSKUInput = By.name("sku");
    private final By submitButton = By.xpath("//button[@type='submit']");


    public AddProductPage enterProductName(String name) {
        return this;
    }

    public AddProductPage enterProductPrice(int price) {
        return this;
    }

    public AddProductPage enterProductStock(int stock) {
        return this;
    }

    public AddProductPage enterProductSKU(int sku) {
        return this;
    }

    public ProductsPage clickSubmitButton() {
        click(submitButton);
        return new ProductsPage();
    }
}