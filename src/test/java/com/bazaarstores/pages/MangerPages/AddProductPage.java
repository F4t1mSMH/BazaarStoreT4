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
    private final By successMessage = By.xpath("//*[@class='toast-message']");
    private final By errorMessage = By.cssSelector("//section[@id='multiple-column-form']//div[contains(@class, 'alert')]");


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

    public AddProductPage clickSubmitButton() {
        click(submitButton);
        return new AddProductPage();
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