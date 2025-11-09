package com.bazaarstores.pages;

import org.openqa.selenium.By;

public class ProductsPage extends DashboardPage {
    private By addProductButton = By.cssSelector("a[href*='product/create']");
    private By editButton = By.xpath("//*[@id=\"table-bordered\"]/div/div/div/div/table/tbody/tr[6]/td[6]/button[1]");
    private By deleteButton = By.xpath("//*[@id=\"table-bordered\"]/div/div/div/div/table/tbody/tr[6]/td[6]/button[2]");
    private final String PRODUCT_TEMPLATE = "//td[contains(text(), '{0}')]";
    public void clickAddProductButton() {
        click(addProductButton);
    }
    public boolean isProductDisplayed(String productName) {
        By productLocator = By.xpath(PRODUCT_TEMPLATE.replace("{0}", productName));

        return isDisplayed(productLocator);
    }
    public void clickEditButton() {
    }

    public void clickDeleteButton() {

    }

}