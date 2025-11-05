package com.bazaarstores.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {

    // By Locators
    private final By dashboard = By.xpath("//div[@class='products-grid']");
    private final By profileVisitChart = By.xpath("//div[@class='card-body']");
    private final By welcomeMessage = By.cssSelector(".welcome-message, [class*='welcome']");
    private final By profileLink = By.cssSelector("a[href*='profile'], button:contains('Profile')");
    private final By ordersLink = By.cssSelector("a[href*='orders'], button:contains('Orders')");
    private final By productsLink = By.cssSelector("a[href*='products'], button:contains('Products')");
    private final By logoutButton = By.cssSelector("button:contains('Logout'), a:contains('Logout')");
    private final By userName = By.cssSelector(".user-name, [class*='username']");


    // Navigation Methods
    public void clickProfileLink() {
        click(profileLink);
    }

    public void clickOrdersLink() {
        click(ordersLink);
    }

    public void clickProductsLink() {
        click(productsLink);
    }

    public LoginPage clickLogout() {
        click(logoutButton);
        return new LoginPage();
    }

    // Verification Methods
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
}