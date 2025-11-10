package com.bazaarstores.pages;

import com.bazaarstores.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ViewCartPage extends BasePage {

    private final By cartIcon = By.cssSelector(".cart-icon");
    private final By viewCartLink = By.linkText("VIEW CART");
    private final By cartItem = By.cssSelector(".cart-item");
    private final By cartItemName = By.cssSelector(".cart-item-name");
    private final By cartItemPrice = By.cssSelector(".cart-item-price");
    private final By cartSubtotal = By.cssSelector(".cart-subtotal");
    private final By removeItemButton = By.cssSelector(".remove-item");
    private final By emptyCartMessage = By.xpath("//*[contains(text(), 'empty') or contains(text(),'no items')]");
    private final By confirmCartButton = By.id("clear-all");
    private final By swalTitle = By.id("swal2-title");
    private final By swalContainer = By.className("swal2-container");





    private final By cartWrapper = By.cssSelector(".cart-wrapper");


    public By getCartIconLocator() {
        return cartIcon;
    }

    public void hoverOverCartIcon() {
      hoverOver(cartIcon);
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(viewCartLink));
            System.out.println(" Cart dropdown appeared and View Cart link visible");
        } catch (Exception e) {
            System.out.println(" View Cart link did not appear after hover: " + e.getMessage());
            throw new RuntimeException("View Cart link failed to appear after hovering over cart icon.", e);
        }

    }



    public ViewCartPage clickViewCartLink() {
        click(viewCartLink);
        return this;
    }



    public boolean isViewCartLinkDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
            WebElement viewCartLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("a.view-cart")));
            return viewCartLink.isDisplayed();
        } catch (Exception e) {
            System.out.println(" View Cart link not found: " + e.getMessage());
            return false;
        }
    }


    public boolean isCartItemDisplayed() {
        hoverOver(cartIcon);
        return isDisplayed(cartItem);
    }


    public boolean isCartPageDisplayed() {
        return isDisplayed(cartSubtotal);
    }

    public String getCartItemName() {
        return getText(cartItemName);
    }

    public String getCartItemPrice() {
        return getText(cartItemPrice);
    }

    public String getCartTotal() {
        return getText(cartSubtotal);
    }

    public void removeItemFromCart() {
        click(removeItemButton);
    }


    private final By swalMessage = By.id("swal2-html-container");

    public String getAlertMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
            WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-html-container")));
            return message.getText();
        } catch (TimeoutException e) {
            System.out.println("disappeared before reading");
            return "";
        }
    }



    public void clickConfirmCartButton() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(confirmCartButton));
        confirmBtn.click();
    }

    public String getSuccessAlertTitle() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(swalTitle));
        return title.getText();
    }




}
