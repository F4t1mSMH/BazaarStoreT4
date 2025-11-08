package com.bazaarstores.pages;

import com.bazaarstores.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CustomerFavoritesPage extends BasePage {

    // Locators -US07-
    private final By favoriteProducts = By.cssSelector(".favorites-grid , .product-card");
    private final By emptyfavoriteList = By.xpath("//*[contains(., 'no favorite product')]");
    private final By productName = By.cssSelector(".product-name");
    private final By favoriteHeartIcon = By.cssSelector(".favorite-icon.active");


    public boolean hasFavoriteProducts() {
        List<WebElement> products = Driver.getDriver().findElements(favoriteProducts);
        return !products.isEmpty();
    }

    public List<String> getFavoriteProductNames() {
        List<String> names = new ArrayList<>();
        for (WebElement el : findElements(productName)) {
            names.add(el.getText());
        }
        return names;
    }
    public void clickHeartIconOnFirstFavorite() {
        List<WebElement> hearts =Driver.getDriver().findElements(favoriteHeartIcon);
        if (!hearts.isEmpty()) {
            hearts.get(0).click();
            System.out.println(" Clicked heart icon on a favorite product");
        } else {
            throw new RuntimeException("No heart icons found on the favorites page");
        }
    }

    public boolean isEmptyMessageDisplayed() {
        List<WebElement> message = Driver.getDriver().findElements(emptyfavoriteList);
        return !message.isEmpty() && message.getFirst().isDisplayed();
    }


}