package com.bazaarstores.pages;

import com.bazaarstores.pages.LoginPage.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class DashboardPage extends BasePage {

    // By Locators
    private final By dashboard = By.xpath("//div[@class='products-grid']");
    private final By profileVisitChart = By.xpath("//div[@class='card-body']");
    //div[@class='logout']
    private final By welcomeMessage = By.cssSelector(".welcome-message, [class*='welcome']");
    private final By profileLink = By.cssSelector("a[href*='profile'], button:contains('Profile')");
    private final By ordersLink = By.cssSelector("a[href*='orders'], button:contains('Orders')");
    private final By productsLink = By.cssSelector("a[href*='products'], button:contains('Products')");
    private final By logoutButton = By.cssSelector("button:contains('Logout'), a:contains('Logout')");
    private final By logoutButton1 = By.linkText("Log Out");
    private final By userName = By.cssSelector(".user-name, [class*='username']");
    private final By StoresButton = By.linkText("Store");
    private final By mangerProductsLink = By.xpath("//a[contains(@href, 'products') or contains(., 'Products')]");

    private final By addToCartButtons = By.cssSelector("button.add-to-cart, button[data-action='add-to-cart']"); // ✅ buttons for adding products
    private final By successMessage = By.cssSelector(".toast-message, .success-message, [class*='added-successfully']");

    private final By prfileicon =By.xpath("//div[@class='profile-icon']");


    // Navigation Methods
    public void clickProfileLink() {
        click(profileLink);
    }

    public DashboardPage movetousericon(){
    hoverOver(prfileicon);
    return this;
    }

    public DashboardPage movetologoutButton(){
        hoverOver(logoutButton1);
        return this;
    }

    public void clickOrdersLink() {
        click(ordersLink);
    }

    public void clickProductsLink() {
        click(productsLink);
    }

    public void clickMangerProductsLink() {
        clickWithJS(mangerProductsLink);
    }

    public LoginPage clickLogout() {
        click(logoutButton1);
        return new LoginPage();
    }

    public StoresPage clickStores() {

        clickWithJS(StoresButton);
        return new StoresPage();
    }

    public void addFirstProductToCart() {
        waitForElementToBeVisible(dashboard); // Ensure products are loaded
        List<WebElement> addButtons = findElements(addToCartButtons);

        if (!addButtons.isEmpty()) {
            addButtons.get(0).click();

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        } else {
            throw new RuntimeException("No products found to add to cart!");
        }

    }


    public boolean isDashboardPageDisplayed() {
        return isDisplayed(dashboard);
    }

    public boolean isWelcomeMessageDisplayed() {
        return isDisplayed(welcomeMessage);
    }

    public String getWelcomeMessageText() {
        return getText(welcomeMessage);
    }

    public String getUserName() {
        return getText(userName);
    }

    public boolean isProfileLinkDisplayed() {
        return isDisplayed(profileLink);
    }

    public boolean isOrdersLinkDisplayed() {
        return isDisplayed(ordersLink);
    }

    public boolean isProductsLinkDisplayed() {
        return isDisplayed(productsLink);
    }

    public boolean isProfileVisitChartDisplayed() {
        return isDisplayed(profileVisitChart);
    }

    public boolean isSuccessMessageDisplayed() {
        return isDisplayed(successMessage);
    }

    public String getSuccessMessageText() {
        return getText(successMessage);
    }
}
