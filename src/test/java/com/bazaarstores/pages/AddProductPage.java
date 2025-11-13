package com.bazaarstores.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.codehaus.groovy.tools.shell.util.Preferences.clear;

public class AddProductPage extends ProductsPage{
    private final By productNameInput = By.name("name");
    private final By productPriceInput = By.name("price");
    private final By productStockInput = By.name("stock");
    private final By productSKUInput = By.name("sku");
    private final By categoryDropdown = By.name("category_id");
    private final By submitButton = By.xpath("//button[@type='submit']");
    private final By successMessage = By.xpath("//*[@class='toast-message']");
    private final By errorMessage = By.xpath("//section[@id='multiple-column-form']//div[contains(@class, 'alert')]");

    public AddProductPage selectDropdownByValue(By locator, int value) {
        WebElement element = findElement(locator);
        Select select = new Select(element);
        select.selectByValue(String.valueOf(value));
        return this;
    }

    public AddProductPage selectProductCategory(int categoryId) {
        return selectDropdownByValue(categoryDropdown, categoryId);
    }

    public AddProductPage enterAllProductDetails(String name, int price, int stock, int sku, int categoryId) {
        enterProductName(name);
        enterProductPrice(price);
        enterProductStock(stock);
        enterProductSKU(sku);
        selectProductCategory(categoryId);
        return this;
    }

    public AddProductPage enterProductName(String name) {
        clear(productNameInput);
        sendKeys(productNameInput, name);
        return this;
    }

    public AddProductPage enterProductPrice(int price) {
        clear(productPriceInput);
        sendKeys(productPriceInput, String.valueOf(price));
        return this;
    }

    public AddProductPage enterProductStock(int stock) {
        clear(productStockInput);
        sendKeys(productStockInput, String.valueOf(stock));
        return this;
    }

    public AddProductPage enterProductSKU(int sku) {
        clear(productSKUInput);
        sendKeys(productSKUInput, String.valueOf(sku));
        return this;
    }

    public AddProductPage clickSubmitButton() {
        clickWithJS(submitButton);
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

    public AddProductPage waitForFormToLoad() {
        waitForElementToBeVisible(submitButton);
        return this;
    }
    public AddProductPage clearProductPrice() {
        clear(productPriceInput);
        return this;
    }
    public AddProductPage clearProductStock() {
        clear(productStockInput);
        return this;
    }
    public AddProductPage clearProductSku() {
        clear(productSKUInput);
        return this;
    }
}