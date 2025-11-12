package com.bazaarstores.pages;

import org.openqa.selenium.By;

public class ProductsPage extends DashboardPage {
    private By addProductButton = By.cssSelector("a[href*='product/create']");
    private final String productTable = "//td[contains(text(), '{0}')]";
    private final String editButton = "//tr[td[contains(.,'%s')]]//i[@class='bi bi-pencil-square']";
    private final String deleteButton = "//tr[td[contains(.,'%s')]]//i[@class='bi bi-trash3']";
    private final String confirmDelete = "//button[normalize-space(text())='Yes, delete it!']";
    private final String cancelDelete = "//button[normalize-space(text())='Cancel']";

    public void clickAddProductButton() {
        clickWithJS(addProductButton);
    }
    public void waitForProductsPageToLoad() {
        waitForElementToBeVisible(addProductButton);
    }

    public void clickEditButtonByName(String productName) {
        By editLocator = By.xpath(String.format(editButton,productName));
        clickWithJS(editLocator);
    }

    public void clickDeleteButtonByName(String productName) {
        By deleteLocator = By.xpath(String.format(deleteButton, productName));
        clickWithJS(deleteLocator);
    }

    public boolean isProductDisplayed(String productName) {
        By productLocator = By.xpath(productTable.replace("{0}", productName));
        return isDisplayed(productLocator);
    }
    public void clickConfirmDeleteButton() {
        click(By.xpath(confirmDelete));
    }

    public void clickCancelDeleteButton() {
        click(By.xpath(cancelDelete));
    }

    public void searchProduct(String productToVerifyName) {

    }

}