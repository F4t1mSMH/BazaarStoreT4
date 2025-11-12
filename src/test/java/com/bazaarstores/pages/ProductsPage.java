package com.bazaarstores.pages;

import org.openqa.selenium.By;

public class ProductsPage extends DashboardPage {
    private By addProductButton = By.xpath("//*[@id=\"main\"]/div/div/div/div[2]/button");
    private final String productTable = "//td[contains(text(), '{0}')]";
    private final String editButton = "//tr[td[contains(text(), '{0}')]]//button[1]";
    private final String deleteButton = "//tr[td[contains(text(), '{0}')]]//i[@class='bi bi-trash3']";
    private final String confirmDelete = "//tr[td[contains(text(), '{0}')]]//button[2]";
    private final String cancelDelete = "//button[normalize-space()='Cancel']";

    public void clickAddProductButton() {
        clickWithJS(addProductButton);
    }

    public void clickEditButtonByName(String productName) {
        By editLocator = By.xpath(editButton.replace("{0}", productName));
        click(editLocator);
    }

    public void clickDeleteButtonByName(String productName) {
        By deleteLocator = By.xpath(deleteButton.replace("{0}", productName));
        click(deleteLocator);
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