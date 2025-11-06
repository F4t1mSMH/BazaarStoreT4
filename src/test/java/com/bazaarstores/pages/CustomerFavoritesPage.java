package com.bazaarstores.pages;

import com.bazaarstores.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CustomerFavoritesPage extends BasePage {

    // Locators -US07-
    private final By favoriteProducts = By.cssSelector(".favorites-grid , .product-card");
    private final By emptyfavoriteList = By.xpath("//*[contains(., 'no favorite product')]");


    //Check if favorites page has any favorite products
    public boolean hasFavoriteProducts() {
        List<WebElement> products = Driver.getDriver().findElements(favoriteProducts);
        return !products.isEmpty();
    }
    public boolean isEmptyMessageDisplayed() {
        List<WebElement> message = Driver.getDriver().findElements(emptyfavoriteList);
        return !message.isEmpty() && message.getFirst().isDisplayed();
    }
}