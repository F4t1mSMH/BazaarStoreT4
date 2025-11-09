package com.bazaarstores.pages;

import com.bazaarstores.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CustomerFavoritesPage extends BasePage {

    // Locators -US07-
    private final By favoriteProducts = By.cssSelector(".favorites-grid , .product-card");
    private final By emptyfavoriteList = By.xpath("//*[contains(., 'no favorite product')]");
    private final By productName = By.cssSelector(".product-name");
    private final By favoriteHeartIcon = By.cssSelector(".favorite-icon");

//    ------------------------------------------------US07-------------------------------------------------------

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

    public String getProductName(){
WebElement name = Driver.getDriver().findElement(productName);
        String productName = name.getText();
        return productName;

    }




    public void clickHeartIconOnFirstProduct() {
        List<WebElement> hearts = Driver.getDriver().findElements(favoriteHeartIcon);
        if (!hearts.isEmpty()) {
            WebElement lastClickedHeart =hearts.getLast();
            lastClickedHeart.click();
            new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(3))
                    .until(ExpectedConditions.elementToBeClickable(lastClickedHeart));
            System.out.println("Clicked heart icon on the first product");
        } else {
            throw new RuntimeException("No heart icons found on the customer page!");
        }
    }



    // test for remove all product

    public void removeAllFavoriteProducts() {
        List<WebElement> hearts = Driver.getDriver().findElements(favoriteHeartIcon);

        while (!hearts.isEmpty()) {
            try {

                WebElement firstHeart = hearts.getFirst();
                firstHeart.click();
                System.out.println("Removed" + " - "+ getProductName() + " - "+"favorite product");

                WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(3));
                wait.until(ExpectedConditions.stalenessOf(firstHeart));

                hearts = Driver.getDriver().findElements(favoriteHeartIcon);

            } catch (Exception e) {
                System.out.println("Error removing a favorite product: " + e.getMessage());
                break;
            }
            System.out.println(" All favorite products have been removed.");
        }

    }



    public boolean isEmptyMessageDisplayed() {
        List<WebElement> message = Driver.getDriver().findElements(emptyfavoriteList);
        return !message.isEmpty() && message.getFirst().isDisplayed();
    }


}