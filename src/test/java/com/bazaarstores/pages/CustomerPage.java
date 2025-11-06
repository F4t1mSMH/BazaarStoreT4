package com.bazaarstores.pages;

import com.bazaarstores.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;



public class CustomerPage extends BasePage {

    // Locators -US04-
    private final By productCards = By.cssSelector(".product-card");
    private final By productName = By.cssSelector(".product-name");
    private final By productPrice = By.cssSelector(".current-price");
    private final By productImage = By.cssSelector(".product-image");
    private final By productDescription = By.cssSelector(".product-description");
    private final By productContainer = By.cssSelector(".products-grid");
    // Locators -US07-
    private final By heartIcon = By.cssSelector(".favorite-icon");
    private final By filledHeartIcon = By.cssSelector(".favorite-icon.active");
    private final By clickFavoritesPage = By.linkText("My Favorites");

    // Verify page is displayed
    public boolean isCustomerPageDisplayed() {
        return isDisplayed(productContainer);
    }


    // Check if all products are visible
    public boolean areAllProductsVisible() {
        List<WebElement> cards = findElements(productCards);
        if (cards.isEmpty()) return false;

        for (WebElement card : cards) {
            if (!card.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    public boolean areProductNamesVisible() {
        for (WebElement element : findElements(productName)) {
            if (!element.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    public boolean areProductPricesVisible() {
        for (WebElement element : findElements(productPrice)) {
            if (!element.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    public boolean areProductImagesVisible() {
        for (WebElement element : findElements(productImage)) {
            if (!element.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    public boolean areProductDescriptionsVisible() {
        for (WebElement element : findElements(productDescription)) {
            if (!element.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    // load page preformance
    public boolean waitUntilProductsAreVisible(int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeoutSeconds));
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productCards));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    //-----------------------------------------------------------------
    public void clickHeartIconOnFirstProduct() {
        List<WebElement> hearts = findElements(heartIcon);
        if (!hearts.isEmpty()) {
            hearts.get(0).click();
        } else {
            throw new RuntimeException(" No heart icons found on the customer page!");
        }

        }
    public void clickFavoritesLink() {
        WebElement clickMyFavorites = findElement(clickFavoritesPage);
        clickMyFavorites.click();

    }
    }

