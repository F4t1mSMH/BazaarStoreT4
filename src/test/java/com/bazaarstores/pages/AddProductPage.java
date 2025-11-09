package com.bazaarstores.pages;

import org.openqa.selenium.By;

public class AddProductPage extends ProductsPage {
    private final By productNameInput = By.name("name");
    private final By productPriceInput = By.name("price");
    private final By productStockInput = By.name("stock");
    private final By productSKUInput = By.name("sku");
    private final By submitButton = By.xpath("//button[@type='submit']");
    private final By successMessage = By.xpath("//*[@class='toast-message']");
    private final By errorMessage = By.xpath("//div[contains(@class, 'alert') or contains(@class, 'error')]");

    public AddProductPage enterProductName(String name) {
        sendKeys(productNameInput, name);
        return this;
    }

    public AddProductPage enterProductPrice(int price) {
        sendKeys(productPriceInput, String.valueOf(price));
        return this;
    }

    public AddProductPage enterProductStock(int stock) {
        sendKeys(productStockInput, String.valueOf(stock));
        return this;
    }

    public AddProductPage enterProductSKU(int sku) {
        sendKeys(productSKUInput, String.valueOf(sku));
        return this;
    }

    public AddProductPage clickSubmitButton() {
        clickWithJS(submitButton);
        return this;
    }


    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorMessage);
    }

    public String getErrorMessageText() {
        return getText(errorMessage);
    }

    public boolean isSuccessMessageDisplayed() {
        return isDisplayed(successMessage);
    }

    public String getSuccessMessageText() {
        return getText(successMessage);
    }
}